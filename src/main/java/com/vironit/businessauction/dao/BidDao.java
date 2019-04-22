package com.vironit.businessauction.dao;

import com.vironit.businessauction.entity.Bid;
import com.vironit.businessauction.entity.BidStatus;
import com.vironit.businessauction.entity.Lot;
import com.vironit.businessauction.entity.User;

import java.util.List;

/**
 * Created by user on 10.02.2019.
 */
public interface BidDao extends BaseDao<Bid> {
    List<Bid> getBidsByStatus(BidStatus bidStatus);
    List<Bid> getBidsByUser(User user);
    List<Bid> getBidsByLot(Lot lot);
    void deleteBidByUserId(Long userId);
}
