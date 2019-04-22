package com.vironit.businessauction.dto;

import com.vironit.businessauction.entity.Bid;
import com.vironit.businessauction.entity.Lot;
import com.vironit.businessauction.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class TradeHistory {
    private List<LotDto> lotList;
    private List<BidDto> bidList;
    private Long userId;
}
