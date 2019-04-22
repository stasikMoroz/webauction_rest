package com.vironit.businessauction.springcontroller.restcontroller;

import com.vironit.businessauction.dto.*;
import com.vironit.businessauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @DeleteMapping("/{id}")//
    @PreAuthorize("(hasRole('ROLE_USER') and #id == authentication.details.id) or hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")//
    @PreAuthorize("(hasRole('ROLE_USER') and #id == authentication.details.id) or hasRole('ROLE_ADMIN')")
    public void updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);
    }

    @GetMapping("/{id}")//
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public UserDto getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/all")//
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDto> getAllUsers() {
        return userService.getListOfUsers();
    }

    @GetMapping("/status")//
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDto> getUsersByStatus(@RequestBody UserStatusDto userStatusDto) {
        return userService.getUsersByStatus(userStatusDto);
    }

    @PutMapping("/topUp/{id}")//
    @PreAuthorize("hasRole('ROLE_USER') and #id == authentication.details.id")
    public void topUpWallet(@PathVariable Long id, @RequestBody WalletUserDto walletUserDto) {
        userService.topUpWallet(id, walletUserDto);
    }

    @GetMapping("/history/{id}")//
    @PreAuthorize("(hasRole('ROLE_USER') and #id == authentication.details.id) or hasRole('ROLE_ADMIN')")
    public TradeHistory getUserHistory(@PathVariable Long id) {
        return userService.getUserHistory(id);
    }

    @PutMapping("/{id}/{lotId}")//
    @PreAuthorize("hasRole('ROLE_USER') and #id == authentication.details.id")
    public void payForTheLot(@PathVariable Long id, @PathVariable Long lotId) {
        userService.payForTheLot(id, lotId);
    }

    @GetMapping
    public UserDto userAuthentication(@RequestBody UserAuthenticationDto userAuthenticationDto) {//TODO удалить
        return userService.userIsPresent(userAuthenticationDto);
    }

}
