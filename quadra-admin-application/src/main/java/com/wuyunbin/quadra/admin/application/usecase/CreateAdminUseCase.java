package com.wuyunbin.quadra.admin.application.usecase;

import com.wuyunbin.quadra.admin.domain.Admin;

public interface CreateAdminUseCase {
    Admin create(String username, String rawPassword);
}

