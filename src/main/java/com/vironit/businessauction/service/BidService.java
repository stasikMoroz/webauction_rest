package com.vironit.businessauction.service;

import com.vironit.businessauction.dto.BidDto;
import com.vironit.businessauction.entity.Bid;
import com.vironit.businessauction.entity.User;

import java.util.List;

public interface BidService {
    BidDto create(BidDto bidDto, Long lotId, Long userId);
    void delete(Long id);
    BidDto findById(Long id);
    void updateBid(Long id, BidDto bidDto);
    List<BidDto> getListOfBids();
    List<BidDto> getUsersBids(Long id);
    void activateBid(Long id);
}
