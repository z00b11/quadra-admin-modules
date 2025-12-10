package com.wuyunbin.quadra.admin.application.service;

import com.wuyunbin.quadra.admin.application.usecase.CreateAdminUseCase;
import com.wuyunbin.quadra.admin.domain.Admin;
import com.wuyunbin.quadra.admin.domain.port.AdminRepository;
import com.wuyunbin.quadra.admin.domain.service.AdminDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建管理员应用服务
 * 说明：
 * - 将“唯一性校验 + 密码策略 + 默认状态”下沉到领域服务，
 *   本服务只负责调用领域服务构建聚合并持久化。
 */
@Service
public class CreateAdminService implements CreateAdminUseCase {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminDomainService adminDomainService;

    @Override
    public Admin create(String username, String rawPassword) {
        Admin admin = adminDomainService.createAdmin(username, rawPassword,null);
        return adminRepository.save(admin);
    }
}
