package com.wuyunbin.quadra.admin.adapter.out.persistence;

import com.wuyunbin.quadra.admin.adapter.out.persistence.entity.AdminEntity;
import com.wuyunbin.quadra.admin.domain.Admin;
import com.wuyunbin.quadra.admin.domain.port.AdminRepository;
import com.wuyunbin.quadra.admin.domain.value.Avatar;
import com.wuyunbin.quadra.admin.domain.value.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AdminRepositoryAdapter implements AdminRepository {

    @Autowired
    private AdminJpaRepository jpaRepository;

    @Override
    public List<Admin> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Admin save(Admin admin) {
        AdminEntity entity = toEntity(admin);
        AdminEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<Admin> saveAll(List<Admin> admins) {
        List<AdminEntity> entities = admins.stream().map(this::toEntity).collect(Collectors.toList());
        return jpaRepository.saveAll(entities).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Admin> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Admin> findByUsername(String username) {
        return jpaRepository.findByUsername(username).map(this::toDomain);
    }

    @Override
    public List<Admin> findAllByIdIn(java.util.Collection<Long> ids) {
        return jpaRepository.findAllByIdIn(ids).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public com.wuyunbin.quadra.admin.domain.value.PageResult<Admin> page(int page, int size) {
        Page<AdminEntity> p = jpaRepository.findAll(PageRequest.of(page, size));
        List<Admin> content = p.map(this::toDomain).getContent();
        return new com.wuyunbin.quadra.admin.domain.value.PageResult<>(
                content,
                p.getNumber(),
                p.getSize(),
                p.getTotalElements(),
                p.getTotalPages()
        );
    }

    private Admin toDomain(AdminEntity e) {
        Admin a = new Admin();
        a.setId(e.getId());
        a.setUsername(e.getUsername());
        Password p = new Password();
        p.setValue(e.getPassword());
        a.setPassword(p);
        Avatar av = new Avatar();
        av.setUrl(e.getAvatar());
        a.setAvatar(av);
        a.setEnabled(e.isEnabled());
        a.setDeleted(e.isDeleted());
        a.setCreatedAt(e.getCreatedAt());
        a.setUpdatedAt(e.getUpdatedAt());
        a.setDeletedAt(e.getDeletedAt());
        return a;
    }

    private AdminEntity toEntity(Admin a) {
        AdminEntity e = new AdminEntity();
        e.setId(a.getId());
        e.setUsername(a.getUsername());
        e.setPassword(a.getPassword() != null ? a.getPassword().getValue() : null);
        e.setAvatar(a.getAvatar() != null ? a.getAvatar().getUrl() : null);
        e.setEnabled(a.isEnabled());
        e.setDeleted(a.isDeleted());
        e.setCreatedAt(a.getCreatedAt());
        e.setUpdatedAt(a.getUpdatedAt());
        e.setDeletedAt(a.getDeletedAt());
        return e;
    }
}
