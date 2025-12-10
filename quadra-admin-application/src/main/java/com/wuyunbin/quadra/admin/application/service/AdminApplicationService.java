package com.wuyunbin.quadra.admin.application.service;

import com.wuyunbin.quadra.admin.application.usecase.*;
import com.wuyunbin.quadra.admin.domain.Admin;
import com.wuyunbin.quadra.admin.domain.port.AdminRepository;
import com.wuyunbin.quadra.admin.domain.service.AdminDomainService;
import com.wuyunbin.quadra.admin.domain.value.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminApplicationService implements
        UpdateAdminUseCase,
        DisableAdminsUseCase,
        SoftDeleteAdminsUseCase,
        GetAdminByIdUseCase,
        GetAdminByUsernameUseCase,
        ListAdminsPageUseCase,
        GetAdminListUseCase {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private AdminDomainService adminDomainService;

    @Override
    /**
     * 更新聚合：将“用户名唯一性校验、密码策略校验”委托给领域服务，
     * 本方法负责持久化与事件发布。
     */
    public Admin update(Long id, String username, String avatar, String newPassword) {
        Admin admin = adminDomainService.updateAggregate(id, username, avatar, newPassword);
        Admin saved = adminRepository.save(admin);
        List<Object> events = admin.pullDomainEvents();
        publishEvents(admin, events);
        return saved;
    }

    @Override
    public void disable(List<Long> ids) {
        List<Admin> admins = adminRepository.findAllByIdIn(ids);
        // 批量编排下沉到领域服务，统一应用聚合内规则
        adminDomainService.disableAll(admins);
        adminRepository.saveAll(admins);
        admins.forEach(a -> {
            List<Object> events = a.pullDomainEvents();
            publishEvents(a, events);
        });
    }

    @Override
    public void softDelete(List<Long> ids) {
        List<Admin> admins = adminRepository.findAllByIdIn(ids);
        // 批量软删编排下沉到领域服务
        adminDomainService.softDeleteAll(admins);
        adminRepository.saveAll(admins);
        admins.forEach(a -> {
            List<Object> events = a.pullDomainEvents();
            publishEvents(a, events);
        });
    }

    private void publishEvents(Admin admin, List<Object> events) {
        if (events == null || events.isEmpty()) return;
        log.info("Publishing {} domain events for admin id={}", events.size(), admin.getId());
        events.forEach(e -> {
            log.info("Publishing event: {}", e.getClass().getSimpleName());
            publisher.publishEvent(e);
        });
    }

    @Override
    public Admin get(Long id) {
        return adminDomainService.idVerify(id);
    }

    @Override
    public Admin get(String username) {
        return adminDomainService.usernameVerify(username);
    }

    @Override
    public PageResult<Admin> page(int page, int size) {
        return adminRepository.page(page, size);
    }

    @Override
    public List<Admin> getList() {
        return adminRepository.findAll();
    }
}
