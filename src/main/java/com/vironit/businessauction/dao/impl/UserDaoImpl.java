package com.vironit.businessauction.dao.impl;

import com.vironit.businessauction.dao.UserDao;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.entity.UserStatus;
import com.vironit.businessauction.exception.LoginOrPasswordIncorrectException;
import com.vironit.businessauction.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class UserDaoImpl implements UserDao {

    private List<User> userList;

    public UserDaoImpl() {
        this.userList = InitDao.getUserList();
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(userList);
    }

    @Override
    public List<User> getUsersByStatus(UserStatus userStatus) {
        return userList.stream()
                .filter(user -> user.getUserStatus().equals(userStatus))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkUserLogin(String login) {
        return userList.stream()
                .anyMatch(user -> user.getLogin().equals(login));

//                .filter(user -> user.getLogin().equals(login))
//                .findFirst().orElseThrow(() -> new LoginAlredyExistException("User with login " + login));
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Optional<User> userAuthentication(String login, String password) {
        return userList.stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
//                .orElseThrow(() -> new LoginOrPasswordIncorrectException("Login or password incorrect!"));
    }

    @Override
    public User save(User user) {

        Long uniqueId= userList.stream()
                .map(User:: getId).max(Long::compareTo).orElse(new Long(0))+1;
        user.setId(uniqueId);
        userList.add(user);
        return user;
    }

    @Override
    public User getById(Long id) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst().orElseThrow(() -> new UserNotFoundException(id + ""));
    }

    @Override
    public void update(User user) {
        User userFromDb = userList.stream()
                            .filter(userDb -> userDb.getId() == user.getId())
                            .findFirst().orElseThrow(() -> new UserNotFoundException(user.getId() + ""));

        Collections.replaceAll(userList,userFromDb,user);
    }

    @Override
    public void delete(Long id) {
        User userFromDb = userList.stream()
                            .filter(userDb -> userDb.getId() == id)
                            .findFirst().orElseThrow(() -> new UserNotFoundException(id + ""));
        userList.remove(userFromDb);
    }
}
