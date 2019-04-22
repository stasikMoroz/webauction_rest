package com.vironit.businessauction.serviceTest;

import com.vironit.businessauction.configuration.TestConfiguration;
import com.vironit.businessauction.dto.AdminAuthenticationDto;
import com.vironit.businessauction.dto.AdminDto;
import com.vironit.businessauction.exception.AdminNotFoundException;
import com.vironit.businessauction.exception.LoginAlredyExistException;
import com.vironit.businessauction.exception.LoginOrPasswordIncorrectException;
import com.vironit.businessauction.service.AdminService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.stream.Collectors;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class AdminServiceImplTest {//TODO удалить

    @Autowired
    private AdminService adminService;

    private AdminDto adminDto;

    @BeforeClass
    public static void primaryInit() {

    }

    @Before
    public void init() {
        adminDto = new AdminDto();
        adminDto.setName("Boris");
        adminDto.setLogin("Konan");
        adminDto.setPassword("1232ujuj");
    }

    @Test
    public void addAdminTest() {
        AdminDto addedAdminDto = adminService.addAdmin(this.adminDto);
        Assert.assertEquals(addedAdminDto, adminService.findAdminById(addedAdminDto.getId()));
        adminService.deleteAdmin(addedAdminDto.getId());
    }

    @Test
    public void addAdminWithExistLoginTest() {
        AdminDto addedAdminDto = adminService.addAdmin(this.adminDto);
        Assert.assertThrows(LoginAlredyExistException.class, () -> adminService.addAdmin(this.adminDto));
        adminService.deleteAdmin(addedAdminDto.getId());
    }

    @Test
    public void findAdminByNotExistIdTest() {
        Long notExistId = adminService.getAllAdmins().stream()
                .map(AdminDto::getId)
                .reduce(1L, (x, y) -> x + y);
        Assert.assertThrows(AdminNotFoundException.class, () -> adminService.findAdminById(notExistId));
    }

    @Test
    public void deleteAdminTest() {
        AdminDto addedAdminDto = adminService.addAdmin(this.adminDto);
        Assert.assertEquals(addedAdminDto, adminService.findAdminById(addedAdminDto.getId()));

        adminService.deleteAdmin(addedAdminDto.getId());
        Assert.assertThrows(AdminNotFoundException.class, () -> adminService.findAdminById(addedAdminDto.getId()));
    }

    @Test
    public void deleteAdminByNotExistIdTest() {
        Long notExistId = adminService.getAllAdmins().stream()
                .map(AdminDto::getId)
                .reduce(1L, (x, y) -> x + y);
        Assert.assertThrows(AdminNotFoundException.class, () -> adminService.deleteAdmin(notExistId));
    }

    @Test
    public void firstAdminIsPresentTest() {
        AdminDto addedAdminDto = adminService.addAdmin(this.adminDto);
        AdminAuthenticationDto adminAuthenticationDto = new AdminAuthenticationDto();
        adminAuthenticationDto.setLogin(addedAdminDto.getLogin());
        adminAuthenticationDto.setPassword(addedAdminDto.getPassword());
        Assert.assertEquals(addedAdminDto, adminService.adminIsPresent(adminAuthenticationDto));
        adminService.deleteAdmin(addedAdminDto.getId());
    }
    
    @Test
    public void findAdminByIncorrectLoginOrPassword() {
        String notExistLogin = adminService.getAllAdmins().stream()
                .map(AdminDto::getLogin)
                .collect(Collectors.joining());
        String notExistPassword = adminService.getAllAdmins().stream()
                .map(AdminDto::getPassword)
                .collect(Collectors.joining());
        AdminAuthenticationDto adminAuthenticationDto = new AdminAuthenticationDto();
        adminAuthenticationDto.setLogin(notExistLogin);
        adminAuthenticationDto.setPassword(notExistPassword);
        Assert.assertThrows(LoginOrPasswordIncorrectException.class, () -> adminService.adminIsPresent(adminAuthenticationDto));
    }

//    @Test
//    public void secondAdminIsPresentTest() {
//        AdminDto addedAdminDto = adminService.addAdmin(this.adminDto);
//        Assert.assertTrue(adminService.adminIsPresent(this.adminDto.getLogin()));
//        adminService.deleteAdmin(addedAdminDto.getId());
//    }
//
//    @Test
//    public void findAdminByNotExistLoginTest() {
//        String notExistLogin = adminService.getAllAdmins().stream()
//                .map(AdminDto::getLogin)
//                .collect(Collectors.joining());
//        Assert.assertFalse(adminService.adminIsPresent(notExistLogin));
//    }

    @Test
    public void updateAdminTest() {
        AdminDto addedAdminDto = adminService.addAdmin(this.adminDto);
        AdminDto updatedAdminDto = new AdminDto();
        updatedAdminDto.setLogin("Gerakl");
        updatedAdminDto.setPassword("887766IOP");
        updatedAdminDto.setName(addedAdminDto.getName());

        adminService.updateAdmin(addedAdminDto.getId(), updatedAdminDto);
        Assert.assertEquals(updatedAdminDto.getPassword(), adminService.findAdminById(addedAdminDto.getId()).getPassword());
        adminService.deleteAdmin(addedAdminDto.getId());
    }

    @Test
    public void updateAdminByNotExistIdTest() {
        AdminDto addedAdminDto = adminService.addAdmin(this.adminDto);
        Long notExistId = adminService.getAllAdmins().stream()
                .map(AdminDto::getId)
                .reduce(1L, (x, y) -> x + y);
        AdminDto updatedAdminDto = new AdminDto();
        updatedAdminDto.setName("Leonardo");
        updatedAdminDto.setLogin("Lion");
        updatedAdminDto.setPassword("oit528");

        Assert.assertThrows(AdminNotFoundException.class, () -> adminService.updateAdmin(notExistId, updatedAdminDto));
        adminService.deleteAdmin(addedAdminDto.getId());
    }
}
