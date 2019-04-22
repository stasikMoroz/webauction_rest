package com.vironit.businessauction.dao.implHibernate;

import com.vironit.businessauction.dao.LotDao;
import com.vironit.businessauction.entity.*;
import com.vironit.businessauction.exception.LotNotFoundException;
import com.vironit.businessauction.exception.UserNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LotDaoImpl extends AbstractBaseDao<Lot> implements LotDao {

//    @Autowired
//    private SessionFactory sessionFactory;

    public LotDaoImpl() {
        setClass(Lot.class);
    }

    @Override
    public List<Lot> getAllUsersLots(User user) {
        //try (Session session = sessionFactory.openSession()) {
            return getCurrentSession().createQuery("FROM Lot l WHERE l.user.id=?1", Lot.class)
                    .setParameter(1, user.getId())
                    .getResultList();
        //}
    }

    @Override
    public List<Lot> getLotsByStatus(LotStatus lotStatus) {
//        try (Session session = sessionFactory.openSession()) {
            return getCurrentSession().createQuery("FROM Lot l WHERE l.status=?1", Lot.class)
                    .setParameter(1, lotStatus)
                    .list();
//        }
    }

    @Override
    public List<Lot> getLotsByCategory(Category category) {
//        try (Session session = sessionFactory.openSession()) {
            return getCurrentSession().createQuery("FROM Lot l WHERE l.category=?1", Lot.class)
                    .setParameter(1, category)
                    .list();
//        }
    }

    @Override
    public List<Lot> getLotsByName(String name) {
        return getCurrentSession().createQuery("FROM Lot l WHERE l.description LIKE ?1", Lot.class)
                .setParameter(1, "%" + name + "%")
                .list();
    }

    @Override
    public void deleteLotByUserId(Long userId) {
//        try (Session session = sessionFactory.openSession()) {
            //Transaction transaction = session.beginTransaction();
            getCurrentSession().createQuery("DELETE Lot l WHERE l.user.id=?1")
                    .setParameter(1, userId)
                    .executeUpdate();
//            transaction.commit();
//        }
    }


}
