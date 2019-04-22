package com.vironit.businessauction.dao.impl;

import com.vironit.businessauction.dao.BidDao;
import com.vironit.businessauction.entity.Bid;
import com.vironit.businessauction.entity.BidStatus;
import com.vironit.businessauction.entity.Lot;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.exception.BidNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class BidDaoImpl implements BidDao {

    private List<Bid> bidList;

    public BidDaoImpl() {
        bidList = InitDao.getBidList();
    }

    @Override
    public Bid save(Bid bid) {

        Long uniqueId= bidList.stream()
                .map(Bid :: getId).max(Long::compareTo).orElse(new Long(0))+1;
        bid.setId(uniqueId);
        bidList.add(bid);
        return bid;
    }

    @Override
    public Bid getById(Long id) {
        return bidList.stream()
                .filter(bid -> bid.getId() == id)
                .findFirst().orElseThrow(() -> new BidNotFoundException(id + ""));
    }

    @Override
    public void update(Bid bid) {
        Bid bidFromDb = bidList.stream()
                .filter(bidDb -> bidDb.getId() == bid.getId())
                .findFirst().orElseThrow(() -> new BidNotFoundException(bid.getId() + ""));

        Collections.replaceAll(bidList, bidFromDb, bid);

    }

    @Override
    public void delete(Long id) {
        Bid bidFromDb = bidList.stream()
                .filter(bid -> bid.getId() == id)
                .findFirst().orElseThrow(() -> new BidNotFoundException(id + ""));
        bidList.remove(bidFromDb);
    }

    @Override
    public List<Bid> getAll() {
        return new ArrayList<>(bidList);
    }

    @Override
    public List<Bid> getBidsByStatus(BidStatus bidStatus) {
        return bidList.stream()
                .filter(bid -> bid.getBidStatus().equals(bidStatus))
                .collect(Collectors.toList());
    }

    @Override
    public List<Bid> getBidsByUser(User user) {
        return bidList.stream()
                .filter(bid -> bid.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<Bid> getBidsByLot(Lot lot) {
        return bidList.stream()
                .filter(bid -> bid.getLot().equals(lot))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBidByUserId(Long userId) {
        List<Bid> bids = bidList.stream()
                .filter(bid -> bid.getUser().getId() == userId)
                .collect(Collectors.toList());

        bidList.removeAll(bids);
    }
}
