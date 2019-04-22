package com.vironit.businessauction.dao;

import com.vironit.businessauction.dao.BaseDao;
import com.vironit.businessauction.entity.Feedback;
import com.vironit.businessauction.entity.FeedbackStatus;
import com.vironit.businessauction.entity.User;

import java.util.List;

public interface FeedbackDao extends BaseDao<Feedback> {
    List<Feedback> getFeedbacksByUser(User user);
    List<Feedback> getFeedbacksByStatus(FeedbackStatus feedbackStatus);
    void deleteFeedbackByUserId(Long userId);
}
