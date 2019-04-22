package com.vironit.businessauction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.vironit.businessauction.dto.LotDto;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@ToString(callSuper = true, includeFieldNames = false)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lot", schema = "webauction_storage", catalog = "webauction_db")
public class Lot extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "start_price")
    private Double startPrice;
    @Column(name = "current_price")
    private Double currentPrice;
    @Column(name = "date_of_start_trading")
    private LocalDateTime dateOfStartTrading;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")

    @Column(name = "date_of_end_trading")
    private LocalDateTime dateOfEndTrading;
    @Column(name = "last_day_of_pay")
    private LocalDateTime lastDayOfPay;
    //    @Transient
//    private TimeLeft timeLeft;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private LotStatus status;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "user_who_won_lot_id")
    private Long userWhoWonLotId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    public void initLot(LotDto lotDto) {

    }
}
