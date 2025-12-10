package com.wuyunbin.quadra.admin.domain;

import com.wuyunbin.quadra.admin.domain.event.AdminPasswordChangedEvent;
import com.wuyunbin.quadra.admin.domain.value.Avatar;
import com.wuyunbin.quadra.admin.domain.value.Password;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class Admin {
    private Long id;
    private Password password;
    private String username;
    private Avatar avatar;

    private boolean enabled;
    private boolean deleted;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private final List<Object> domainEvents = new ArrayList<>();

    public void enable() {
        if (deleted) {
            throw new IllegalStateException("deleted admin cannot be enabled");
        }
        this.enabled = true;
        this.updatedAt = Instant.now();
    }

    public void disable() {
        if (deleted) {
            throw new IllegalStateException("deleted admin cannot be disabled");
        }
        this.enabled = false;
        this.updatedAt = Instant.now();
    }

    public void softDelete() {
        if (enabled) {
            throw new IllegalStateException("disable before soft delete");
        }
        this.deleted = true;
        this.deletedAt = Instant.now();
        this.updatedAt = this.deletedAt;
    }

    public void updateProfile(String newUsername, String avatarUrl) {
        if (deleted) {
            throw new IllegalStateException("deleted admin cannot be updated");
        }
        if (newUsername != null && !newUsername.isBlank()) {
            this.username = newUsername;
        }
        if (avatarUrl != null) {
            if (this.avatar == null) {
                this.avatar = new Avatar();
            }
            this.avatar.setUrl(avatarUrl);
        }
        this.updatedAt = Instant.now();
    }

    public void changePassword(String rawPassword) {
        if (deleted) {
            throw new IllegalStateException("deleted admin cannot change password");
        }
        String md5 = md5Hex(rawPassword);
        if (this.password == null) {
            this.password = new Password();
        }
        this.password.setValue(md5);
        this.updatedAt = Instant.now();

        // 发布事件 - 纯java生撸
        this.addEvent(new AdminPasswordChangedEvent(this.id, this.updatedAt));
    }

    public boolean verifyPassword(String rawPassword) {
        String md5 = md5Hex(rawPassword);
        return this.password != null && md5.equals(this.password.getValue());
    }

    private String md5Hex(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 not available", e);
        }
    }

    public List<Object> pullDomainEvents() {
        List<Object> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }

    private void addEvent(Object e) {
        System.out.println("事件发布...");
        domainEvents.add(e);
    }
}
