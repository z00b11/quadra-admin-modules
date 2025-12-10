package com.wuyunbin.quadra.admin.domain.event;

import java.time.Instant;

public record AdminPasswordChangedEvent(Long adminId, Instant occurredAt) {}
