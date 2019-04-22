package com.vironit.businessauction.springcontroller.restcontroller;

import com.vironit.businessauction.dto.AdminAuthenticationDto;
import com.vironit.businessauction.dto.AdminDto;
import com.vironit.businessauction.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {//TODO удалить

    @Autowired
    private AdminService adminService;

    @GetMapping
    public AdminDto adminAuthentication(@RequestBody AdminAuthenticationDto adminAuthenticationDto) {
        return adminService.adminIsPresent(adminAuthenticationDto);
    }

    @PostMapping
    public AdminDto addAdmin(@RequestBody AdminDto adminDto) {
        return adminService.addAdmin(adminDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }

    @PutMapping("/{id}")
    public void updateAdmin(@PathVariable Long id, @RequestBody AdminDto adminDto) {
        adminService.updateAdmin(id, adminDto);
    }

    @GetMapping("/{id}")
    public AdminDto getAdmin(@PathVariable Long id) {
        return adminService.findAdminById(id);
    }

    @GetMapping("/all")
    public List<AdminDto> getAllAdmins() {
        return adminService.getAllAdmins();
    }
}
