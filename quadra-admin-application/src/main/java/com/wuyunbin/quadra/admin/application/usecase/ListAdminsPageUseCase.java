package com.wuyunbin.quadra.admin.application.usecase;

import com.wuyunbin.quadra.admin.domain.Admin;
import com.wuyunbin.quadra.admin.domain.value.PageResult;

import java.util.List;

public interface ListAdminsPageUseCase {
    PageResult<Admin> page(int page, int size);
}
