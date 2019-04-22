package com.vironit.businessauction.service;

import com.vironit.businessauction.dto.FeedbackDto;
import com.vironit.businessauction.entity.Feedback;
import com.vironit.businessauction.entity.User;

import java.util.List;

public interface FeedbackService {
    FeedbackDto addFeedback(FeedbackDto feedbackDto, Long userId);
    void deleteFeedback(Long id);
    List<FeedbackDto> getListOfFeedbackByUser(Long userId);
    void updateFeedback(Long id, FeedbackDto feedbackDto);
    FeedbackDto findById(Long id);
    List<FeedbackDto> getAllFeedbacks();
}
