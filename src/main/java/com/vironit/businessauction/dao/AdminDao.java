package com.vironit.businessauction.dao;

import com.vironit.businessauction.entity.Admin;
import com.vironit.businessauction.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on 10.02.2019.
 */

public interface AdminDao extends BaseDao<Admin> {//TODO удалить
    Optional<Admin> adminAuthentication(String login, String password);
    boolean checkAdminLogin(String login);
}
