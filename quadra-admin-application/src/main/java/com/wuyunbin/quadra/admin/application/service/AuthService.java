package com.wuyunbin.quadra.admin.application.service;

import com.wuyunbin.quadra.admin.application.usecase.LoginUseCase;
import com.wuyunbin.quadra.admin.application.usecase.LogoutUseCase;
import com.wuyunbin.quadra.admin.domain.Admin;
import com.wuyunbin.quadra.admin.domain.port.AdminRepository;
import com.wuyunbin.quadra.admin.common.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements LoginUseCase, LogoutUseCase {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public String login(String username, String password) {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("invalid username or password"));
        if (!admin.verifyPassword(password)) {
            throw new IllegalArgumentException("invalid username or password");
        }
        return JwtUtil.generateToken(admin.getId(), admin.getUsername(), Boolean.TRUE);
    }

    @Override
    public void logout(Long userId) {
        // no-op for stateless JWT; placeholder for future session blacklist
    }
}

