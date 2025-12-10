package com.wuyunbin.quadra.admin.application.usecase;

import com.wuyunbin.quadra.admin.domain.Admin;

public interface GetAdminByUsernameUseCase {
    Admin get(String username);
}

