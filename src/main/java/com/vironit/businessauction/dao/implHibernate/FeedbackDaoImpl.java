package com.vironit.businessauction.dao.implHibernate;

import com.vironit.businessauction.dao.FeedbackDao;
import com.vironit.businessauction.entity.Bid;
import com.vironit.businessauction.entity.Feedback;
import com.vironit.businessauction.entity.FeedbackStatus;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.exception.FeedbackNotFoundException;
import com.vironit.businessauction.exception.LotNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackDaoImpl extends AbstractBaseDao<Feedback> implements FeedbackDao {

//    @Autowired
//    private SessionFactory sessionFactory;

    public FeedbackDaoImpl() {
        setClass(Feedback.class);
    }

    @Override
    public List<Feedback> getFeedbacksByUser(User user) {
//        try (Session session = sessionFactory.openSession()) {
            List<Feedback> feedbackList = getCurrentSession().createQuery("FROM Feedback f WHERE f.user.id=?1", Feedback.class)
                    .setParameter(1, user.getId())
                    .getResultList();
            return feedbackList;
//        }
    }

    @Override
    public List<Feedback> getFeedbacksByStatus(FeedbackStatus feedbackStatus) {
//        try (Session session = sessionFactory.openSession()) {
            List<Feedback> list = getCurrentSession().createQuery("FROM Feedback f WHERE f.feedbackStatus=?1", Feedback.class)
                    .setParameter(1, feedbackStatus)
                    .list();
            return list;
//        }
    }

    @Override
    public void deleteFeedbackByUserId(Long userId) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
            getCurrentSession().createQuery("DELETE Feedback f WHERE f.user.id=?1")
                    .setParameter(1, userId)
                    .executeUpdate();
//            transaction.commit();
//        }
    }

//    @Override
//    public Feedback save(Feedback feedback) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.save(feedback);
//            transaction.commit();
//        }
//        return feedback;
//    }
//
//    @Override
//    public Feedback getById(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            Feedback feedback = session.get(Feedback.class, id);
//            if (feedback == null) {
//                throw new FeedbackNotFoundException();
//            }
//            return feedback;
//        }
//    }
//
//    @Override
//    public void update(Feedback feedback) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.update(feedback);
//            transaction.commit();
//        }
//    }
//
//    @Override
//    public void delete(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            Feedback feedback = session.get(Feedback.class, id);
//            session.delete(feedback);
//            transaction.commit();
//        } catch (IllegalArgumentException e) {
//            throw new FeedbackNotFoundException();
//        }
//    }
//
//    @Override
//    public List<Feedback> getAll() {
//        try (Session session = sessionFactory.openSession()) {
//            List<Feedback> list = session.createQuery("FROM Feedback u", Feedback.class).list();
//            return list;
//        }
//    }
}
