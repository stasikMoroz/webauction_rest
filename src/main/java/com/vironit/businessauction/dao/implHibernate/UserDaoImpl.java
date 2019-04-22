package com.vironit.businessauction.dao.implHibernate;

import com.vironit.businessauction.dao.UserDao;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.entity.UserStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractBaseDao<User> implements UserDao {

    public UserDaoImpl() {
        setClass(User.class);
    }

    @Override
    public List<User> getUsersByStatus(UserStatus userStatus) {
//        try (Session session = sessionFactory.openSession()) {
            List<User> list = getCurrentSession().createQuery("FROM User u WHERE u.userStatus=?1", User.class)
                    .setParameter(1, userStatus)
                    .list();
            return list;
//        }
    }

    @Override
    public boolean checkUserLogin(String login) {
//        try (Session session = sessionFactory.openSession()) {
            boolean a = getCurrentSession().createQuery("SELECT u.login FROM User u WHERE u.login=?1")
                    .setParameter(1, login)
                    .stream()
                    .findAny()
                    .isPresent();
            return a;
//        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return getCurrentSession().createQuery("FROM User u WHERE u.login=?1", User.class)
                .setParameter(1, login)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<User> userAuthentication(String login, String password) {
        //                    .orElseThrow(() -> new LoginOrPasswordIncorrectException("Login or password incorrect!"));

        return getCurrentSession().createQuery("FROM User u WHERE u.login=?1 and u.password=?2", User.class)
                        .setParameter(1, login)
                        .setParameter(2, password)
                        .stream()
                        .findFirst();
    }

}
