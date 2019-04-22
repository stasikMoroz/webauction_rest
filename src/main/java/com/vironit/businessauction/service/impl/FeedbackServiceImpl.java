package com.vironit.businessauction.service.impl;

import com.vironit.businessauction.dao.FeedbackDao;
import com.vironit.businessauction.dao.UserDao;
import com.vironit.businessauction.dto.FeedbackDto;
import com.vironit.businessauction.entity.Feedback;
import com.vironit.businessauction.entity.FeedbackStatus;
import com.vironit.businessauction.entity.Lot;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.exception.FeedbackNotFoundException;
import com.vironit.businessauction.exception.UserNotFoundException;
import com.vironit.businessauction.security.detail.UserDetailsImpl;
import com.vironit.businessauction.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackDao feedbackDao;
    private UserDao userDao;

    @Autowired
    public FeedbackServiceImpl(FeedbackDao feedbackDao, UserDao userDao) {
        this.feedbackDao = feedbackDao;
        this.userDao = userDao;
    }

    @Override
    public FeedbackDto addFeedback(FeedbackDto feedbackDto, Long userId) {
        User userFromDb = userDao.getById(userId);
        if (userFromDb == null) {
            throw new UserNotFoundException(userId + "");
        }
        Feedback feedback = new Feedback();
        feedback.setUser(userFromDb);
        feedback.setCreatedDateTime(LocalDateTime.now());
        feedback.setMessage(feedbackDto.getMessage());
        feedback.setFeedbackStatus(feedbackDto.getFeedbackStatus());

        return new FeedbackDto(feedbackDao.save(feedback));
    }

    @Override
    public void deleteFeedback(Long id) {
        Feedback feedback = feedbackDao.getById(id);
        if (feedback == null) {
            throw new FeedbackNotFoundException(id + "");
        }
        checkAccessRights(feedback);
        feedbackDao.delete(id);
    }

    @Override
    public List<FeedbackDto> getListOfFeedbackByUser(Long userId) {
        User userFromDb = userDao.getById(userId);
        return feedbackDao.getFeedbacksByUser(userFromDb).stream()
                .map(FeedbackDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void updateFeedback(Long id, FeedbackDto feedbackDto) {
        Feedback feedback = feedbackDao.getById(id);
        if (feedback == null) {
            throw new FeedbackNotFoundException(id + "");
        }
        checkAccessRights(feedback);
        feedback.setUpdatedDateTime(LocalDateTime.now());
        feedback.setFeedbackStatus(feedbackDto.getFeedbackStatus());
        feedback.setMessage(feedbackDto.getMessage());
        feedbackDao.update(feedback);
    }

    @Override
    public FeedbackDto findById(Long id) {
        Feedback feedback = feedbackDao.getById(id);
        if (feedback == null) {
            throw new FeedbackNotFoundException(id + "");
        }
        return new FeedbackDto(feedback);
    }

    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackDao.getAll().stream()
                .map(FeedbackDto::new)
                .collect(Collectors.toList());
    }

    private void checkAccessRights(Feedback feedback) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl details = (UserDetailsImpl) authentication.getDetails();
        if (!details.getAuthorities().stream().findFirst().get().getAuthority().equals("ROLE_ADMIN")) {
            if (!details.getId().equals(feedback.getUser().getId())) {
                throw new AuthorizationServiceException("User with id: " + details.getId() + " doesn't have access to this feedback!");
            }
        }
    }

}
