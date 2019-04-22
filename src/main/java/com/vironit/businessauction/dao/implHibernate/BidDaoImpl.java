package com.vironit.businessauction.dao.implHibernate;

import com.vironit.businessauction.dao.BidDao;
import com.vironit.businessauction.entity.Bid;
import com.vironit.businessauction.entity.BidStatus;
import com.vironit.businessauction.entity.Lot;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.exception.BidNotFoundException;
import com.vironit.businessauction.exception.FeedbackNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BidDaoImpl extends AbstractBaseDao<Bid> implements BidDao {

//    @Autowired
//    private SessionFactory sessionFactory;

    public BidDaoImpl() {
        setClass(Bid.class);
    }

    @Override
    public List<Bid> getBidsByStatus(BidStatus bidStatus) {
//        try (Session session = sessionFactory.openSession()) {
            return getCurrentSession().createQuery("FROM Bid b WHERE b.bidStatus=?1", Bid.class)
                    .setParameter(1, bidStatus)
                    .list();
//        }
    }

    @Override
    public List<Bid> getBidsByUser(User user) {
//        try (Session session = sessionFactory.openSession()) {
            return getCurrentSession().createQuery("FROM Bid b WHERE b.user.id=?1", Bid.class)
                    .setParameter(1, user.getId())
                    .getResultList();
//        }
    }

    @Override
    public List<Bid> getBidsByLot(Lot lot) {
//        try (Session session = sessionFactory.openSession()) {
            return getCurrentSession().createQuery("FROM Bid b WHERE b.lot.id=?1", Bid.class)
                    .setParameter(1, lot.getId())
                    .getResultList();
//        }
    }

    @Override
    public void deleteBidByUserId(Long userId) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
            getCurrentSession().createQuery("DELETE Bid b WHERE b.user.id=?1")
                    .setParameter(1, userId)
                    .executeUpdate();
//            transaction.commit();
//        }
    }


//    @Override
//    public Bid save(Bid bid) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.save(bid);
//            transaction.commit();
//        }
//        return bid;
//    }
//
//    @Override
//    public Bid getById(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            Bid bid = session.get(Bid.class, id);
//            if (bid == null) {
//                throw new BidNotFoundException();
//            }
//            return bid;
//        }
//    }
//
//    @Override
//    public void update(Bid bid) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.update(bid);
//            transaction.commit();
//        }
//    }
//
//    @Override
//    public void delete(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            Bid bid = session.get(Bid.class, id);
//            session.delete(bid);
//            transaction.commit();
//        }
//    }
//
//    @Override
//    public List<Bid> getAll() {
//        try (Session session = sessionFactory.openSession()) {
//            return session.createQuery("FROM Bid b", Bid.class).list();
//        }
//    }
}
