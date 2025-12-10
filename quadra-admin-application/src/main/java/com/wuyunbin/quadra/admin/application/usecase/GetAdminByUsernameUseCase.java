package com.wuyunbin.quadra.admin.application.usecase;

import com.wuyunbin.quadra.admin.domain.Admin;

import java.util.Optional;

public interface GetAdminByUsernameUseCase {
    Optional<Admin> get(String username);
}

