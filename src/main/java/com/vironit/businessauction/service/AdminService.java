package com.vironit.businessauction.service;

import com.vironit.businessauction.dto.AdminAuthenticationDto;
import com.vironit.businessauction.dto.AdminDto;
import com.vironit.businessauction.entity.Admin;
import com.vironit.businessauction.entity.Lot;
import com.vironit.businessauction.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AdminService {//TODO удалить
    AdminDto adminIsPresent(AdminAuthenticationDto adminAuthenticationDto);
    AdminDto addAdmin(AdminDto adminDto);
    void deleteAdmin(Long id);
    void updateAdmin(Long id, AdminDto adminDto);
    AdminDto findAdminById(Long id);
    List<AdminDto> getAllAdmins();
}
