package com.vironit.businessauction.service;

public interface TradeExecutionService {
    void defenitionOfSoldButNotPaidLots();
    void defenitionOfNotSoldLots();
    void defenitionOfNotPaidLots();
    void defenitionOfLostBids();
    void defenitionOfBidsThatWon();
    void banningOfUsersWhoNotPaidOnTime();
}