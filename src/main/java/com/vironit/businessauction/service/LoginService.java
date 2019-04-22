package com.vironit.businessauction.service;

import com.vironit.businessauction.dto.TokenDto;
import com.vironit.businessauction.form.LoginForm;

public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
