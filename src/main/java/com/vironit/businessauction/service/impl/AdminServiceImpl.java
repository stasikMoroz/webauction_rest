package com.vironit.businessauction.service.impl;

import com.vironit.businessauction.dao.AdminDao;
import com.vironit.businessauction.dto.AdminAuthenticationDto;
import com.vironit.businessauction.dto.AdminDto;
import com.vironit.businessauction.entity.Admin;
import com.vironit.businessauction.exception.AdminNotFoundException;
import com.vironit.businessauction.exception.LoginAlredyExistException;
import com.vironit.businessauction.exception.LoginOrPasswordIncorrectException;
import com.vironit.businessauction.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {//TODO удалить

    private AdminDao adminDao;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public AdminDto adminIsPresent(AdminAuthenticationDto adminAuthenticationDto) {
        Optional<Admin> optionalAdmin = adminDao.adminAuthentication(adminAuthenticationDto.getLogin(), adminAuthenticationDto.getPassword());
        Admin admin = optionalAdmin.orElseThrow(LoginOrPasswordIncorrectException::new);
        return new AdminDto(admin);
    }

    @Override
    public AdminDto addAdmin(AdminDto adminDto) {
        if (adminDao.checkAdminLogin(adminDto.getLogin())) {
            throw new LoginAlredyExistException("Admin with login " + adminDto.getLogin());
        } else {
            Admin admin = new Admin();
            admin.setCreatedDateTime(LocalDateTime.now());
            admin.initAdmin(adminDto);
            return new AdminDto(adminDao.save(admin));
        }
    }

    @Override
    public void deleteAdmin(Long id) {
        Admin admin = adminDao.getById(id);
        if (admin == null) {
            throw new AdminNotFoundException(id + "");
        }
        adminDao.delete(id);
    }

    @Override
    public void updateAdmin(Long id, AdminDto adminDto) {
        Admin updatedAdmin = adminDao.getById(id);
        if (updatedAdmin == null) {
            throw new AdminNotFoundException(id + "");
        }
        updatedAdmin.setUpdatedDateTime(LocalDateTime.now());
        updatedAdmin.initAdmin(adminDto);
        adminDao.update(updatedAdmin);
    }

    @Override
    public AdminDto findAdminById(Long id) {
        Admin admin = adminDao.getById(id);
        if (admin == null) {
            throw new AdminNotFoundException(id + "");
        }
        return new AdminDto(admin);
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        return adminDao.getAll().stream()
                .map(AdminDto::new)
                .collect(Collectors.toList());
    }
}
