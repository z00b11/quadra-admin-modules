package com.wuyunbin.quadra.admin.adapter.in.web.dto;

import java.util.List;

public record PageResponseDTO<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {}
