package com.vironit.businessauction.dao.impl;

import com.vironit.businessauction.dao.FeedbackDao;
import com.vironit.businessauction.entity.Feedback;
import com.vironit.businessauction.entity.FeedbackStatus;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.exception.FeedbackNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class FeedbackDaoImpl implements FeedbackDao {

    List<Feedback> feedbackList;

    public FeedbackDaoImpl() {
        feedbackList = InitDao.getFeedbackList();
    }

    @Override
    public Feedback save(Feedback feedback) {

        Long uniqueId= feedbackList.stream()
                .map(Feedback :: getId).max(Long::compareTo).orElse(new Long(0))+1;
        feedback.setId(uniqueId);
        feedbackList.add(feedback);
        return feedback;
    }

    @Override
    public Feedback getById(Long id) {
        return feedbackList.stream()
                .filter(feedback -> feedback.getId() == id)
                .findFirst().orElseThrow(() -> new FeedbackNotFoundException(id + ""));
    }

    @Override
    public void update(Feedback feedback) {
        Feedback feedbackFromDb = feedbackList.stream()
                .filter(feedbackDb -> feedbackDb.getId() == feedback.getId())
                .findFirst().orElseThrow(() -> new FeedbackNotFoundException(feedback.getId() + ""));
        Collections.replaceAll(feedbackList, feedbackFromDb, feedback);
    }

    @Override
    public void delete(Long id) {
        Feedback feedbackFromDb = feedbackList.stream()
                .filter(fedbackDb -> fedbackDb.getId() == id)
                .findFirst().orElseThrow(() -> new FeedbackNotFoundException(id + ""));


        feedbackList.remove(feedbackFromDb);
    }

    @Override
    public List<Feedback> getAll() {
        return new ArrayList<>(feedbackList);
    }

    @Override
    public List<Feedback> getFeedbacksByUser(User user) {
        return feedbackList.stream()
                .filter(feedback -> feedback.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<Feedback> getFeedbacksByStatus(FeedbackStatus feedbackStatus) {
        return feedbackList.stream()
                .filter(feedback -> feedback.getFeedbackStatus().equals(feedbackStatus))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFeedbackByUserId(Long userId) {
        List<Feedback> feedbacks = feedbackList.stream()
                .filter(feedback -> feedback.getUser().getId() == userId)
                .collect(Collectors.toList());

        feedbackList.removeAll(feedbacks);
    }
}
