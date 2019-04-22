package com.vironit.businessauction.service.impl;

import com.vironit.businessauction.service.ExpirationService;
import com.vironit.businessauction.service.TradeExecutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpirationServiceImpl implements ExpirationService {

//    private static final Logger logger = LogManager.getLogger(ExpirationServiceImpl.class);
    private static Logger log = LoggerFactory.getLogger(ExpirationServiceImpl.class);


    @Autowired
    private TradeExecutionService tradeExecutionService;

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(5000);
                tradeExecutionService.defenitionOfSoldButNotPaidLots();
                tradeExecutionService.defenitionOfNotSoldLots();
                tradeExecutionService.defenitionOfNotPaidLots();
                tradeExecutionService.defenitionOfLostBids();
                tradeExecutionService.defenitionOfBidsThatWon();
                tradeExecutionService.banningOfUsersWhoNotPaidOnTime();
            }
            log.info("ExprirationService is interrupted!");
        } catch (InterruptedException e) {
            log.info("ExprirationService is interrupted!", e);
        }
    }

}
