package com.vironit.businessauction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.vironit.businessauction.entity.Bid;
import com.vironit.businessauction.entity.BidStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class BidDto {
    private Long id;
    private Double price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timeToPayForLot;
    private BidStatus bidStatus;
    private Long userId;

    public BidDto(Bid bid) {
        this.id = bid.getId();
        this.price = bid.getPrice();
        this.timeToPayForLot = bid.getTimeToPayForLot();
        this.bidStatus = bid.getBidStatus();
        this.userId = bid.getUser().getId();
    }
}
