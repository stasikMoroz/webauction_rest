package com.vironit.businessauction.security.detail;

import com.vironit.businessauction.dao.UserDao;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;



    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        return new UserDetailsImpl(userDao.findUserByLogin(login).orElseThrow(UserNotFoundException::new));
        Optional<User> userByLogin = userDao.findUserByLogin(login);
        User user = userByLogin.orElseThrow(UserNotFoundException::new);
        LinkedList<GrantedAuthority> roles = new LinkedList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));
        //return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
        return new UserDetailsImpl(user.getLogin(), user.getPassword(), roles, user.getId(), user.getUserStatus());
    }
}
