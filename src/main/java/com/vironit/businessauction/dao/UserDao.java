package com.vironit.businessauction.dao;

import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.entity.UserStatus;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on 10.02.2019.
 */
public interface UserDao extends BaseDao<User> {
    List<User> getUsersByStatus(UserStatus userStatus);
    boolean checkUserLogin(String login);
    Optional<User> findUserByLogin(String login);
    Optional<User> userAuthentication(String login, String password);
}
