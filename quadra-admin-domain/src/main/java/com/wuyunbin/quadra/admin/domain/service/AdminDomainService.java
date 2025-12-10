package com.wuyunbin.quadra.admin.domain.service;

import com.wuyunbin.quadra.admin.domain.Admin;
import com.wuyunbin.quadra.admin.domain.port.AdminRepository;
import com.wuyunbin.quadra.admin.domain.value.Avatar;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 领域服务（Domain Service）
 *
 * 说明：
 * - 当业务规则需要查询仓储（如唯一性校验）、跨聚合编排或策略验证时，
 *   优先放入领域服务；实体（聚合根）保持本地不变式与状态变更。
 * - 本类不依赖 Spring，仅通过构造器注入仓储接口，便于在应用层装配。
 *
 * 使用建议：
 * - 在应用服务中注入并调用本服务，随后统一持久化与发布事件。
 */
public final class AdminDomainService {

    private final AdminRepository adminRepository;

    public AdminDomainService(AdminRepository adminRepository) {
        this.adminRepository = Objects.requireNonNull(adminRepository);
    }

    /**
     * 创建管理员聚合，包含：用户名唯一性校验、设置默认状态、密码加密与事件产生。
     * 应用层负责调用仓储保存与事件发布。
     */
    public Admin createAdmin(String username, String rawPassword,String avatar) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username required");
        }

        if(avatar==null || avatar.isBlank()){
            avatar="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";
        }

        adminRepository.findByUsername(username).ifPresent(a -> {
            throw new IllegalArgumentException("username already exists");
        });

        Admin admin = new Admin();
        admin.updateProfile(username, null);
        admin.changePassword(rawPassword);

        admin.setAvatar(new Avatar(avatar));
        admin.setEnabled(true);
        admin.setDeleted(false);
        Instant now = Instant.now();
        admin.setCreatedAt(now);
        admin.setUpdatedAt(now);
        return admin;
    }

    /**
     * 创建管理员（无头像参数）：沿用默认头像策略。
     */
    public Admin createAdmin(String username, String rawPassword) {
        return createAdmin(username, rawPassword, null);
    }

    /**
     * 用户名唯一性校验；允许“自我保留”场景（更新为原名）。
     * 在更新用户名前调用。
     */
    public void ensureUsernameUnique(String username, Long selfId) {
        if (username == null || username.isBlank()) {
            return; // 空或空白不参与校验，由实体 updateProfile 自行忽略
        }
        adminRepository.findByUsername(username).ifPresent(a -> {
            if (selfId == null || !a.getId().equals(selfId)) {
                throw new IllegalArgumentException("username already exists");
            }
        });
    }

    /**
     * 按策略修改密码：最少 8 位，需包含字母与数字（示例策略）。
     * 通过实体方法执行实际加密与事件产生。
     */
    public void changePasswordWithPolicy(Admin admin, String rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("password required");
        }
        if (rawPassword.length() < 8) {
            throw new IllegalArgumentException("password too short");
        }
        Pattern hasLetter = Pattern.compile(".*[A-Za-z].*");
        Pattern hasDigit = Pattern.compile(".*[0-9].*");
        if (!hasLetter.matcher(rawPassword).matches() || !hasDigit.matcher(rawPassword).matches()) {
            throw new IllegalArgumentException("password must contain letters and digits");
        }
        admin.changePassword(rawPassword);
    }

    /**
     * 头像规范化：为空或空白时返回默认头像 URL。
     */
    public String normalizeAvatar(String avatar) {
        if (avatar == null || avatar.isBlank()) {
            return "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";
        }
        return avatar;
    }

    //做了一个整合，整合了之前写好的api
    public Admin updateAggregate(Long id, String username, String avatar, String newPassword) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("admin not found"));

        ensureUsernameUnique(username, id);

        String normalized = normalizeAvatar(avatar);

        admin.updateProfile(username, normalized);
        if (newPassword != null && !newPassword.isBlank()) {
            changePasswordWithPolicy(admin, newPassword);
        }
        return admin;
    }

    /**
     * 批量禁用编排：遍历聚合并调用实体防御规则。
     * 返回已变更的聚合列表，应用层负责持久化与事件发布。
     */
    public List<Admin> disableAll(List<Admin> admins) {
        if (admins == null) return List.of();
        admins.forEach(Admin::disable);
        return admins;
    }

    /**
     * 批量软删除编排：必要时先禁用，再软删。
     * 返回已变更的聚合列表，应用层负责持久化与事件发布。
     */
    public List<Admin> softDeleteAll(List<Admin> admins) {
        if (admins == null) return List.of();
        admins.forEach(a -> {
            if (a.isEnabled()) a.disable();
            a.softDelete();
        });
        return admins;
    }
}
