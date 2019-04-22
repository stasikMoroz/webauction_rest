package com.vironit.businessauction.dao.implHibernate;

import com.vironit.businessauction.dao.BaseDao;
import com.vironit.businessauction.entity.BaseEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractBaseDao<T extends BaseEntity> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public void setClass(final Class<T> classToSet) {
        clazz = classToSet;
    }

    @Override
    public T save(T t) {
        getCurrentSession().save(t);
        return t;
    }

    @Override
    public T getById(Long id) {
        return getCurrentSession().get(clazz, id);
    }

    @Override
    public void update(T t) {
        getCurrentSession().merge(t);
    }

    @Override
    public void delete(Long id) {
        T t = getCurrentSession().get(clazz, id);
        getCurrentSession().delete(t);
    }

    @Override
    public List<T> getAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    public Session getCurrentSession() {
//        try {
//            if (sessionFactory.isOpen()) {
//                sessionFactory.close();
//            }
            return sessionFactory.getCurrentSession();
//        } catch (HibernateException e) {
//            return sessionFactory.openSession();
//        }
    }
}
