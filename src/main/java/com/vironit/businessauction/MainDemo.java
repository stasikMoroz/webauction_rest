package com.vironit.businessauction;

import com.vironit.businessauction.connection.HibernateConfiguration;
import com.vironit.businessauction.entity.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public class MainDemo {//TODO удалить

    public static void main(String[] args) {
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConfiguration.class);
//        SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
//        Admin admin = new Admin();
//        admin.setName("EMAdmin");
//        admin.setLogin("em");
//        admin.setPassword("ksdfjhg46546");
//
//        Session currentSession = sessionFactory.openSession();
//        Transaction transaction = currentSession.beginTransaction();
//        currentSession.save(admin);
//        transaction.commit();
//        currentSession.close();
    }
}
