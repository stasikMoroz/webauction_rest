package com.vironit.businessauction.dao.implHibernate;

import com.vironit.businessauction.dao.AdminDao;
import com.vironit.businessauction.entity.Admin;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.exception.AdminNotFoundException;
import com.vironit.businessauction.exception.LoginOrPasswordIncorrectException;
import com.vironit.businessauction.exception.LotNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminDaoImpl extends AbstractBaseDao<Admin> implements AdminDao {//TODO удалить

//    @Autowired
//    private SessionFactory sessionFactory;

    public AdminDaoImpl() {
        setClass(Admin.class);
    }

    @Override
    public Optional<Admin> adminAuthentication(String login, String password) {
        //try (Session session = sessionFactory.openSession()) {
            Optional<Admin> adminOptional = getCurrentSession().createQuery("FROM Admin a WHERE a.login=?1 and a.password=?2", Admin.class)
                    .setParameter(1, login)
                    .setParameter(2, password)
                    .stream()
                    .findFirst();
//                    .orElseThrow(() -> new LoginOrPasswordIncorrectException("Login or password incorrect!"));

            return adminOptional;
        //}
    }

    @Override
    public boolean checkAdminLogin(String login) {
       // try (Session session = sessionFactory.openSession()) {
            boolean a = getCurrentSession().createQuery("SELECT a.login FROM Admin a WHERE a.login=?1")
                    .setParameter(1, login)
                    .stream()
                    .findAny()
                    .isPresent();
            return a;
        //}
    }



//    @Override
//    public Admin save(Admin admin) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.save(admin);
//            transaction.commit();
//        }
//        return null;
//    }
//
//    @Override
//    public Admin getById(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            Admin admin = session.get(Admin.class, id);
//            if (admin == null) {
//                throw new AdminNotFoundException();
//            }
//            return admin;
//        }
//    }
//
//    @Override
//    public void update(Admin admin) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.update(admin);
//            transaction.commit();
//        }
//    }
//
//    @Override
//    public void delete(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            Admin admin = session.get(Admin.class, id);
//            session.delete(admin);
//            transaction.commit();
//        }
//    }
//
//    @Override
//    public List<Admin> getAll() {
//        try (Session session = sessionFactory.openSession()) {
//            List<Admin> list = session.createQuery("FROM Admin a", Admin.class).list();
//            return list;
//        }
//    }
}
