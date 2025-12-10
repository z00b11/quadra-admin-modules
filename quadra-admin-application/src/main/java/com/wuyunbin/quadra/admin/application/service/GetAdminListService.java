package com.wuyunbin.quadra.admin.application.service;

import com.wuyunbin.quadra.admin.application.usecase.GetAdminListUseCase;
import com.wuyunbin.quadra.admin.domain.Admin;
import com.wuyunbin.quadra.admin.domain.port.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//todo 这个取名叫Service是不是很奇怪？
@Slf4j
@Service
public class GetAdminListService implements GetAdminListUseCase {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> execute() {
        return adminRepository.findAll();
    }
}
