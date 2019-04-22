package com.vironit.businessauction.springcontroller.restcontroller;

import com.vironit.businessauction.dto.TokenDto;
import com.vironit.businessauction.form.LoginForm;
import com.vironit.businessauction.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginForm loginForm) {
        return loginService.login(loginForm);
    }
}
