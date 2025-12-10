package com.wuyunbin.quadra.admin.application.usecase;

import com.wuyunbin.quadra.admin.domain.Admin;

import java.util.List;

public interface GetAdminListUseCase {
    List<Admin> execute();
}
