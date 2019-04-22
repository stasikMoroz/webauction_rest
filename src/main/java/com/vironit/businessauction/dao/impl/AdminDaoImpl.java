package com.vironit.businessauction.dao.impl;

import com.vironit.businessauction.dao.AdminDao;
import com.vironit.businessauction.entity.Admin;
import com.vironit.businessauction.exception.AdminNotFoundException;
import com.vironit.businessauction.exception.LoginOrPasswordIncorrectException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@Component
public class AdminDaoImpl implements AdminDao {//TODO удалить

    private List<Admin> adminList;

    public AdminDaoImpl() {
        this.adminList = InitDao.getAdminList();
    }

    @Override
    public Admin save(Admin admin) {
        Long uniqueId= adminList.stream()
                .map(Admin :: getId).max(Long::compareTo).orElse(new Long(0))+1;
        admin.setId(uniqueId);
        adminList.add(admin);
        return admin;
    }

    @Override
    public Admin getById(Long id) {
        return adminList.stream()
                .filter(admin -> admin.getId() == id)
                .findFirst().orElseThrow(() -> new AdminNotFoundException(id + ""));
    }

    @Override
    public void update(Admin admin) {
        Admin adminFromDb = adminList.stream()
                .filter(adminDb -> adminDb.getId() == admin.getId())
                .findFirst().orElseThrow(() -> new AdminNotFoundException(admin.getId() + ""));

        Collections.replaceAll(adminList, adminFromDb, admin);
    }

    @Override
    public void delete(Long id) {
        Admin adminFromDb = adminList.stream()
                .filter(adminDb -> adminDb.getId() == id)
                .findFirst().orElseThrow(() -> new AdminNotFoundException(id + ""));
        adminList.remove(adminFromDb);
    }

    @Override
    public List<Admin> getAll() {
        return new ArrayList<>(adminList);
    }

    @Override
    public Optional<Admin> adminAuthentication(String login, String password) {
        return adminList.stream()
                .filter(admin -> admin.getLogin().equals(login))
                .filter(admin -> admin.getPassword().equals(password))
                .findFirst();
//                .orElseThrow(() -> new LoginOrPasswordIncorrectException("Login or password incorrect!"));
    }

    @Override
    public boolean checkAdminLogin(String login) {
        return adminList.stream()
                .anyMatch(admin -> admin.getLogin().equals(login));
    }
}
