package com.wuyunbin.quadra.admin.domain.port;

import com.wuyunbin.quadra.admin.domain.Admin;
import com.wuyunbin.quadra.admin.domain.value.PageResult;
import java.util.List;
import java.util.Optional;

public interface AdminRepository {
    List<Admin> findAll();
    Admin save(Admin admin);
    List<Admin> saveAll(List<Admin> admins);
    Optional<Admin> findById(Long id);
    Optional<Admin> findByUsername(String username);
    List<Admin> findAllByIdIn(java.util.Collection<Long> ids);
    PageResult<Admin> page(int page, int size);
}
