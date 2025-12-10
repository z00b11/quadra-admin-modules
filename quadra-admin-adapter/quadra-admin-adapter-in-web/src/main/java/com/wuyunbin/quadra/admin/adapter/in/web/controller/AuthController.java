package com.wuyunbin.quadra.admin.adapter.in.web.controller;

import com.wuyunbin.quadra.admin.adapter.in.web.dto.LoginRequestDTO;
import com.wuyunbin.quadra.admin.application.usecase.LoginUseCase;
import com.wuyunbin.quadra.admin.application.usecase.LogoutUseCase;
import com.wuyunbin.quadra.admin.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {

    @Autowired
    private LoginUseCase loginUseCase;
    @Autowired
    private LogoutUseCase logoutUseCase;

    @PostMapping("/login")
    @Operation(summary = "登录")
    public Result<String> login(@RequestBody LoginRequestDTO req) {
        String token = loginUseCase.login(req.username(), req.password());
        return Result.success(token);
    }

    @PostMapping("/logout")
    @Operation(summary = "登出")
    public Result<Void> logout(@RequestParam Long userId) {
        logoutUseCase.logout(userId);
        return Result.success();
    }
}
