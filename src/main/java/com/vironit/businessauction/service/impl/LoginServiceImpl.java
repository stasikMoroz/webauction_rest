package com.vironit.businessauction.service.impl;

import com.vironit.businessauction.dao.TokenDao;
import com.vironit.businessauction.dao.UserDao;
import com.vironit.businessauction.dao.implHibernate.TokenDaoImpl;
import com.vironit.businessauction.dto.TokenDto;
import com.vironit.businessauction.entity.Token;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.exception.UserNotFoundException;
import com.vironit.businessauction.form.LoginForm;
import com.vironit.businessauction.service.LoginService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public TokenDto login(LoginForm loginForm) {
        Optional<User> userByLogin = userDao.findUserByLogin(loginForm.getLogin());

        if (userByLogin.isPresent()) {
            User user = userByLogin.get();

            if (passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
                Token token = new Token();
                token.setUser(user);
                token.setValue(RandomStringUtils.random(10, true, true));

                tokenDao.save(token);
                return new TokenDto(token);
            }
        }

        throw new UserNotFoundException();
    }
}
