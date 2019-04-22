package com.vironit.businessauction.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString(callSuper = true, includeFieldNames = false)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "feedback", schema = "webauction_storage", catalog = "webauction_db")
public class Feedback extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "message")
    private String message;
    @Column(name = "feedback_status")
    @Enumerated(EnumType.STRING)
    private FeedbackStatus feedbackStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}
