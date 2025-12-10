package com.wuyunbin.quadra.admin.application.usecase;

import java.util.List;

public interface SoftDeleteAdminsUseCase {
    void softDelete(List<Long> ids);
}

