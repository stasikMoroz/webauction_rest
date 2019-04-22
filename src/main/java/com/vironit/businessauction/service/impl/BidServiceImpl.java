package com.vironit.businessauction.service.impl;

import com.vironit.businessauction.dao.BidDao;
import com.vironit.businessauction.dao.LotDao;
import com.vironit.businessauction.dao.UserDao;
import com.vironit.businessauction.dto.BidDto;
import com.vironit.businessauction.entity.*;
import com.vironit.businessauction.exception.*;
import com.vironit.businessauction.security.detail.UserDetailsImpl;
import com.vironit.businessauction.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BidServiceImpl implements BidService {

    private final BidDao bidDao;
    private final LotDao lotDao;
    private final UserDao userDao;

    @Autowired
    public BidServiceImpl(BidDao bidDao, LotDao lotDao, UserDao userDao) {
        this.bidDao = bidDao;
        this.lotDao = lotDao;
        this.userDao = userDao;
    }

    @Override
    public BidDto create(BidDto bidDto, Long lotId, Long userId) {
        Lot lotFromDb = lotDao.getById(lotId);
        if (lotFromDb == null) {
            throw new LotNotFoundException(lotId + "");
        }
        User userFromDb = userDao.getById(userId);
        if (userId == null) {
            throw new UserNotFoundException(userId + "");
        }
        Bid bid = new Bid();
        bid.setBidStatus(BidStatus.NEW);
        bid.setCreatedDateTime(LocalDateTime.now());
        bid.setPrice(bidDto.getPrice());
        bid.setLot(lotFromDb);
        bid.setUser(userFromDb);
        return new BidDto(bidDao.save(bid));
    }

    @Override
    public void delete(Long id) {
        Bid bid = bidDao.getById(id);
        if (bid == null) {
            throw new BidNotFoundException(id + "");
        }
        checkAccessRights(bid);
        if (bid.getBidStatus().equals(BidStatus.NEW)) {
            bidDao.delete(id);
        } else {
            throw new BidNotHaveNewStatusException("Bid with id: " + bid.getId() + " doesn't have NEW status!");
        }
    }

    @Override
    public BidDto findById(Long id) {
        Bid bid = bidDao.getById(id);
        if (bid == null) {
            throw new BidNotFoundException(id + "");
        }
        checkAccessRights(bid);
        return new BidDto(bid);
    }

    @Override
    public void updateBid(Long id, BidDto bidDto) {
        Bid bid = bidDao.getById(id);
        if (bid == null) {
            throw new BidNotFoundException(id + "");
        }
        checkAccessRights(bid);
        if (bid.getBidStatus().equals(BidStatus.NEW)) {
            bid.setUpdatedDateTime(LocalDateTime.now());
            bid.setPrice(bidDto.getPrice());
            bidDao.update(bid);
        } else {
            throw new BidNotHaveNewStatusException("Bid with id: " + bid.getId() + " doesn't have NEW status!");
        }
    }

    @Override
    public List<BidDto> getListOfBids() {
        return bidDao.getAll().stream()
                .map(BidDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<BidDto> getUsersBids(Long id) {
        return bidDao.getBidsByUser(userDao.getById(id)).stream()
                .map(BidDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void activateBid(Long id) {
        Bid bid = bidDao.getById(id);
        if (bid == null) {
            throw new BidNotFoundException(id + "");
        }
        checkAccessRights(bid);
        if (bid.getPrice() <= bid.getLot().getCurrentPrice()) {
            throw new TradeException(bid.getLot().getId() + "");
        }
        bid.setBidStatus(BidStatus.ACTIVE);
        bid.setTimeToPayForLot(bid.getLot().getLastDayOfPay());
        Lot lot = bid.getLot();
        lot.setCurrentPrice(bid.getPrice());
        lot.setUserWhoWonLotId(bid.getUser().getId());
        lotDao.update(lot);
        bidDao.update(bid);
    }

    private void checkAccessRights(Bid bid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl details = (UserDetailsImpl) authentication.getDetails();
        if (!details.getAuthorities().stream().findFirst().get().getAuthority().equals("ROLE_ADMIN")) {
            if (!details.getId().equals(bid.getUser().getId())) {
                throw new AuthorizationServiceException("User with id: " + details.getId() + " doesn't have access to this bid!");
            }
        }
    }
}
