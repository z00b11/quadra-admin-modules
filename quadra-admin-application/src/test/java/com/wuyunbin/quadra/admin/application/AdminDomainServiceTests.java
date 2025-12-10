package com.wuyunbin.quadra.admin.application;

import com.wuyunbin.quadra.admin.domain.Admin;
import com.wuyunbin.quadra.admin.domain.port.AdminRepository;
import com.wuyunbin.quadra.admin.domain.service.AdminDomainService;
import com.wuyunbin.quadra.admin.domain.value.PageResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * AdminDomainService 行为测试
 */
public class AdminDomainServiceTests {

    @Test
    public void testCreateAdmin_uniqueAndDefaults() {
        InMemoryRepo repo = new InMemoryRepo();
        AdminDomainService service = new AdminDomainService(repo);

        Admin a = service.createAdmin("alice", "Alice123");
        Assertions.assertEquals("alice", a.getUsername());
        Assertions.assertTrue(a.verifyPassword("Alice123"));
        Assertions.assertTrue(a.isEnabled());
        Assertions.assertFalse(a.isDeleted());
        Assertions.assertNotNull(a.getCreatedAt());
        Assertions.assertNotNull(a.getUpdatedAt());

        // 保存后再次创建应报唯一性错误
        repo.save(a);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.createAdmin("alice", "diff1234"));
    }

    @Test
    public void testEnsureUsernameUnique() {
        InMemoryRepo repo = new InMemoryRepo();
        AdminDomainService service = new AdminDomainService(repo);

        Admin a = service.createAdmin("alice", "Alice123");
        a.setId(1L);
        repo.save(a);

        // 保留原名（selfId 相同）应通过
        service.ensureUsernameUnique("alice", 1L);
        // 更换为已存在的他人用户名应失败
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.ensureUsernameUnique("alice", 2L));
    }

    @Test
    public void testChangePasswordWithPolicy() {
        InMemoryRepo repo = new InMemoryRepo();
        AdminDomainService service = new AdminDomainService(repo);
        Admin a = service.createAdmin("bob", "Bob12345");

        // 太短或不包含数字/字母应失败
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.changePasswordWithPolicy(a, "short1"));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.changePasswordWithPolicy(a, "!!!!!!!!"));

        service.changePasswordWithPolicy(a, "NewPass123");
        Assertions.assertTrue(a.verifyPassword("NewPass123"));
    }

    @Test
    public void testBulkDisableAndSoftDelete() {
        InMemoryRepo repo = new InMemoryRepo();
        AdminDomainService service = new AdminDomainService(repo);

        Admin a1 = service.createAdmin("a1", "A1pass123");
        Admin a2 = service.createAdmin("a2", "A2pass123");
        List<Admin> list = Arrays.asList(a1, a2);

        service.disableAll(list);
        Assertions.assertFalse(a1.isEnabled());
        Assertions.assertFalse(a2.isEnabled());

        service.softDeleteAll(list);
        Assertions.assertTrue(a1.isDeleted());
        Assertions.assertTrue(a2.isDeleted());
    }

    // 简易内存实现，仅满足测试所需
    static class InMemoryRepo implements AdminRepository {
        private final Map<Long, Admin> store = new HashMap<>();
        private final AtomicLong seq = new AtomicLong(1);

        @Override
        public List<Admin> findAll() { return new ArrayList<>(store.values()); }

        @Override
        public Admin save(Admin admin) {
            if (admin.getId() == null) admin.setId(seq.getAndIncrement());
            store.put(admin.getId(), clone(admin));
            return clone(admin);
        }

        @Override
        public List<Admin> saveAll(List<Admin> admins) {
            List<Admin> res = new ArrayList<>();
            for (Admin a : admins) res.add(save(a));
            return res;
        }

        @Override
        public Optional<Admin> findById(Long id) {
            Admin a = store.get(id);
            return Optional.ofNullable(a == null ? null : clone(a));
        }

        @Override
        public Optional<Admin> findByUsername(String username) {
            return store.values().stream()
                    .filter(a -> Objects.equals(a.getUsername(), username))
                    .findFirst()
                    .map(this::clone);
        }

        @Override
        public List<Admin> findAllByIdIn(Collection<Long> ids) {
            List<Admin> res = new ArrayList<>();
            for (Long id : ids) {
                findById(id).ifPresent(res::add);
            }
            return res;
        }

        @Override
        public PageResult<Admin> page(int page, int size) {
            List<Admin> all = findAll();
            return new PageResult<>(all, page, size, all.size(), 1);
        }

        private Admin clone(Admin src) {
            Admin a = new Admin();
            a.setId(src.getId());
            a.setUsername(src.getUsername());
            a.setPassword(src.getPassword());
            a.setAvatar(src.getAvatar());
            a.setEnabled(src.isEnabled());
            a.setDeleted(src.isDeleted());
            a.setCreatedAt(src.getCreatedAt());
            a.setUpdatedAt(src.getUpdatedAt());
            a.setDeletedAt(src.getDeletedAt());
            return a;
        }
    }
}

