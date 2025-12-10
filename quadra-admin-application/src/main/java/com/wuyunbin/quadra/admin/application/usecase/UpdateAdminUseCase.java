package com.wuyunbin.quadra.admin.application.usecase;

import com.wuyunbin.quadra.admin.domain.Admin;

public interface UpdateAdminUseCase {
    Admin update(Long id, String username, String avatar, String newPassword);
}

