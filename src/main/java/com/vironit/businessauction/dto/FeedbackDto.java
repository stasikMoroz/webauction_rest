package com.vironit.businessauction.dto;

import com.vironit.businessauction.entity.Feedback;
import com.vironit.businessauction.entity.FeedbackStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class FeedbackDto {
    private Long id;
    private String message;
    private FeedbackStatus feedbackStatus;
    private Long userId;

    public FeedbackDto(Feedback feedback) {
        this.id = feedback.getId();
        this.message = feedback.getMessage();
        this.feedbackStatus = feedback.getFeedbackStatus();
        this.userId = feedback.getUser().getId();
    }
}
