package com.wuyunbin.quadra.admin.application.usecase;

import com.wuyunbin.quadra.admin.domain.Admin;

import java.util.Optional;

public interface GetAdminByIdUseCase {
    Optional<Admin> get(Long id);
}

