package com.wuyunbin.quadra.admin.adapter.in.web.controller;

import com.wuyunbin.quadra.admin.adapter.in.web.dto.*;
import com.wuyunbin.quadra.admin.application.usecase.*;
import com.wuyunbin.quadra.admin.common.Result;
import com.wuyunbin.quadra.admin.domain.Admin;
import com.wuyunbin.quadra.admin.domain.value.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/admin")
@Tag(name = "Admin")
public class AdminController {

    @Autowired
    private GetAdminListUseCase getAdminListUseCase;
    @Autowired
    private CreateAdminUseCase createAdminUseCase;
    @Autowired
    private UpdateAdminUseCase updateAdminUseCase;
    @Autowired
    private DisableAdminsUseCase disableAdminsUseCase;
    @Autowired
    private SoftDeleteAdminsUseCase softDeleteAdminsUseCase;
    @Autowired
    private GetAdminByIdUseCase getAdminByIdUseCase;
    @Autowired
    private GetAdminByUsernameUseCase getAdminByUsernameUseCase;
    @Autowired
    private ListAdminsPageUseCase listAdminsPageUseCase;

    @GetMapping("index")
    @Operation(summary = "管理员列表")
    public Result<List<AdminListRequestDTO>> index(){
        List<Admin> admins = getAdminListUseCase.getList();
        List<AdminListRequestDTO> list = admins.stream()
                .map(a -> new AdminListRequestDTO(
                        a.getId(),
                        a.getUsername(),
                        a.getAvatar() != null ? a.getAvatar().getUrl() : null
                ))
                .collect(Collectors.toList());
        return Result.success(list);
    }

    @PostMapping("create")
    @Operation(summary = "创建管理员")
    public Result<AdminListRequestDTO> create(@RequestBody AdminCreateRequestDTO req) {
        Admin admin = createAdminUseCase.create(req.username(), req.password());
        AdminListRequestDTO dto = new AdminListRequestDTO(
                admin.getId(),
                admin.getUsername(),
                admin.getAvatar() != null ? admin.getAvatar().getUrl() : null
        );
        return Result.success(dto);
    }

    @PutMapping("{id}")
    @Operation(summary = "更新管理员")
    public Result<AdminListRequestDTO> update(@PathVariable Long id, @RequestBody AdminUpdateRequestDTO req) {
        Admin admin = updateAdminUseCase.update(id, req.username(), req.avatar(), req.newPassword());
        return Result.success(new AdminListRequestDTO(admin.getId(), admin.getUsername(), admin.getAvatar() != null ? admin.getAvatar().getUrl() : null));
    }

    @PostMapping("disable")
    @Operation(summary = "批量禁用管理员")
    public Result<Void> disable(@RequestBody BatchIdsRequestDTO req) {
        disableAdminsUseCase.disable(req.ids());
        return Result.success();
    }

    @PostMapping("delete")
    @Operation(summary = "批量软删除管理员")
    public Result<Void> delete(@RequestBody BatchIdsRequestDTO req) {
        softDeleteAdminsUseCase.softDelete(req.ids());
        return Result.success();
    }

    @GetMapping("{id}")
    @Operation(summary = "根据ID查询管理员")
    public Result<AdminListRequestDTO> getById(@PathVariable Long id) {
        Admin a = getAdminByIdUseCase.get(id);
        return Result.success(new AdminListRequestDTO(a.getId(), a.getUsername(), a.getAvatar() != null ? a.getAvatar().getUrl() : null));
    }

    @GetMapping("by-username")
    @Operation(summary = "根据用户名查询管理员")
    public Result<AdminListRequestDTO> getByUsername(@RequestParam String username) {
        Admin a = getAdminByUsernameUseCase.get(username);
        return Result.success(new AdminListRequestDTO(a.getId(), a.getUsername(), a.getAvatar() != null ? a.getAvatar().getUrl() : null));
    }

    @GetMapping("page")
    @Operation(summary = "管理员分页")
    public Result<PageResponseDTO<AdminListRequestDTO>> page(@RequestParam int page, @RequestParam int size) {
        PageResult<com.wuyunbin.quadra.admin.domain.Admin> pr = listAdminsPageUseCase.page(page, size);
        List<AdminListRequestDTO> content = pr.getContent().stream()
            .map(a -> new AdminListRequestDTO(a.getId(), a.getUsername(), a.getAvatar() != null ? a.getAvatar().getUrl() : null))
            .collect(Collectors.toList());
        PageResponseDTO<AdminListRequestDTO> dto = new PageResponseDTO<>(content, pr.getPage(), pr.getSize(), pr.getTotalElements(), pr.getTotalPages());
        return Result.success(dto);
    }
}
