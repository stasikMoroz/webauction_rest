package com.vironit.businessauction.service.impl;

import com.vironit.businessauction.dao.BidDao;
import com.vironit.businessauction.dao.LotDao;
import com.vironit.businessauction.dao.UserDao;
import com.vironit.businessauction.entity.BidStatus;
import com.vironit.businessauction.entity.LotStatus;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.entity.UserStatus;
import com.vironit.businessauction.service.TradeExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class TradeExecutionServiceImpl implements TradeExecutionService {

    private final LotDao lotDao;
    private final BidDao bidDao;
    private final UserDao userDao;

    @Autowired
    public TradeExecutionServiceImpl(LotDao lotDao, BidDao bidDao, UserDao userDao) {
        this.lotDao = lotDao;
        this.bidDao = bidDao;
        this.userDao = userDao;
    }

    @Override
    public void defenitionOfSoldButNotPaidLots() {
        lotDao.getLotsByStatus(LotStatus.ACTIVE).stream()
                .filter(lot -> LocalDateTime.now().isAfter(lot.getDateOfEndTrading()) && lot.getCurrentPrice() > lot.getStartPrice())
                .peek(lot -> lot.setStatus(LotStatus.SOLD_BUT_NOT_PAID))
                .forEach(lot -> lotDao.update(lot));
    }

    @Override
    public void defenitionOfNotSoldLots() {
        lotDao.getLotsByStatus(LotStatus.ACTIVE).stream()
                .filter(lot -> LocalDateTime.now().isAfter(lot.getDateOfEndTrading()) && lot.getCurrentPrice().equals(lot.getStartPrice()))
                .peek(lot -> lot.setStatus(LotStatus.PAUSED))
                .forEach(lot -> lotDao.update(lot));
    }

    @Override
    public void defenitionOfNotPaidLots() {
        lotDao.getLotsByStatus(LotStatus.SOLD_BUT_NOT_PAID).stream()
                .filter(lot -> LocalDateTime.now().isAfter(lot.getLastDayOfPay()))
                .peek(lot -> {lot.setStatus(LotStatus.PAUSED);
                    lot.setLastDayOfPay(null);
                    lot.setCurrentPrice(lot.getStartPrice());})
                .forEach(lot -> lotDao.update(lot));
    }

    @Override
    public void defenitionOfLostBids() {
        bidDao.getAll().stream()
                .filter(bid -> bid.getBidStatus().equals(BidStatus.ACTIVE) || bid.getBidStatus().equals(BidStatus.WON))
                .filter(bid -> bid.getPrice() < bid.getLot().getCurrentPrice())
                .peek(bid -> bid.setBidStatus(BidStatus.LOST))
                .forEach(bid -> bidDao.update(bid));
    }

    @Override
    public void defenitionOfBidsThatWon() {
        bidDao.getBidsByStatus(BidStatus.ACTIVE).stream()
                .filter(bid -> bid.getPrice().equals(bid.getLot().getCurrentPrice()))
                .peek(bid -> {bid.setBidStatus(BidStatus.WON);
                    bid.setTimeToPayForLot(bid.getLot().getLastDayOfPay());})
                .forEach(bid -> bidDao.update(bid));
    }

    @Override
    public void banningOfUsersWhoNotPaidOnTime() {
        bidDao.getBidsByStatus(BidStatus.WON).stream()
                .filter(bid -> bid.getLot().getLastDayOfPay() == null || bid.getTimeToPayForLot().isBefore(bid.getLot().getLastDayOfPay()))
                .forEach(bid -> {
                    User user = bid.getUser();
                    user.setUserStatus(UserStatus.BAN);
                    userDao.update(user);});
    }
}
