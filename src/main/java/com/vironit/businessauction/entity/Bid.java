package com.vironit.businessauction.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString(callSuper = true, includeFieldNames = false)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bid", schema = "webauction_storage", catalog = "webauction_db")
public class Bid extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "price")
    private Double price;
    @Column(name = "bid_status")
    @Enumerated(EnumType.STRING)
    private BidStatus bidStatus;
    @Column(name = "time_to_pay_for_lot")
    private LocalDateTime timeToPayForLot;
    @ManyToOne()
    @JoinColumn(name = "lot_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Lot lot;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
}

