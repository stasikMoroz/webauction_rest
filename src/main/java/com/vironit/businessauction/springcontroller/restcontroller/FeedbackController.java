package com.vironit.businessauction.springcontroller.restcontroller;

import com.vironit.businessauction.dto.FeedbackDto;
import com.vironit.businessauction.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public FeedbackDto addFeedback(@PathVariable Long userId, @RequestBody FeedbackDto feedbackDto) {
        return feedbackService.addFeedback(feedbackDto, userId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<FeedbackDto> getUsersFeedbacks(@PathVariable Long userId) {
        return feedbackService.getListOfFeedbackByUser(userId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void updateFeedback(@PathVariable Long id, @RequestBody FeedbackDto feedbackDto) {
        feedbackService.updateFeedback(id, feedbackDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public FeedbackDto getFeedback(@PathVariable Long id) {
        return feedbackService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }


}
