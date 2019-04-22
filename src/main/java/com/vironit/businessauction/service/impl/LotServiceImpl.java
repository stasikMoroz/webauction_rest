package com.vironit.businessauction.service.impl;

import com.vironit.businessauction.dao.LotDao;
import com.vironit.businessauction.dao.UserDao;
import com.vironit.businessauction.dto.*;
import com.vironit.businessauction.entity.*;
import com.vironit.businessauction.exception.LotCanNotBeActivatedException;
import com.vironit.businessauction.exception.LotNotFoundException;
import com.vironit.businessauction.exception.LotNotHavePauseOrNewStatusException;
import com.vironit.businessauction.exception.UserNotFoundException;
import com.vironit.businessauction.security.detail.UserDetailsImpl;
import com.vironit.businessauction.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LotServiceImpl implements LotService {

    private LotDao lotDao;
    private UserDao userDao;

    @Autowired
    public LotServiceImpl(LotDao lotDao, UserDao userDao) {
        this.lotDao = lotDao;
        this.userDao = userDao;
    }

    @Override
    public LotDto create(LotDto lotDto, Long userId) {
        User userFromDb = userDao.getById(userId);
        if (userFromDb == null) {
            throw new UserNotFoundException(userId + "");
        }
        Lot lot = new Lot();
        lot.setUser(userFromDb);
        lot.setStatus(LotStatus.NEW);
        lot.setCreatedDateTime(LocalDateTime.now());
        lot.setStartPrice(lotDto.getStartPrice());
        lot.setCurrentPrice(lot.getStartPrice());
        lot.setDescription(lotDto.getDescription());
        lot.setCategory(lotDto.getCategory());

        return new LotDto(lotDao.save(lot));
    }

    @Override
    public List<LotDto> getLotsByName(LotNameDto lotNameDto) {
        return lotDao.getLotsByName(lotNameDto.getName()).stream()
                .map(LotDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public LotDto findById(Long id) {
        Lot lot = lotDao.getById(id);
        if (lot == null) {
            throw new LotNotFoundException(id + "");
        }

        return new LotDto(lot);
    }

    @Override
    public List<LotDto> getListOfAllLots() {
        return lotDao.getAll().stream()
                .map(LotDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<LotDto> getUsersLots(Long userId) {
        return lotDao.getAllUsersLots(userDao.getById(userId)).stream()
                .map(LotDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<LotDto> getLotsByStatus(LotStatusDto lotStatusDto) {
        return lotDao.getLotsByStatus(lotStatusDto.getLotStatus()).stream()
                .map(LotDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<LotDto> getLotsByCategory(LotCategoryDto lotCategoryDto) {
        return lotDao.getLotsByCategory(lotCategoryDto.getLotCategory()).stream()
                .map(LotDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLotById(Long id) {
        Lot deletedLot = lotDao.getById(id);
        if (deletedLot == null) {
            throw new LotNotFoundException(id + "");
        }
        checkAccessRights(deletedLot);
        if (deletedLot.getStatus().equals(LotStatus.PAUSED)
                || deletedLot.getStatus().equals(LotStatus.NEW)) {
            lotDao.delete(deletedLot.getId());
        } else {
            throw new LotNotHavePauseOrNewStatusException(id + "");
        }
    }

    @Override
    public void updateLot(Long id, LotDto lotDto) {
        Lot lot = lotDao.getById(id);
        if (lot == null) {
            throw new LotNotFoundException(id + "");
        }
        checkAccessRights(lot);
        if (lot.getStatus().equals(LotStatus.PAUSED)
                || lot.getStatus().equals(LotStatus.NEW)) {
            lot.setUpdatedDateTime(LocalDateTime.now());
            lot.setCategory(lotDto.getCategory());
            lot.setDescription(lotDto.getDescription());
            lot.setStartPrice(lotDto.getStartPrice());
            lot.setCurrentPrice(lotDto.getStartPrice());
            lotDao.update(lot);
        } else {
            throw new LotNotHavePauseOrNewStatusException(id + "");
        }
    }

    @Override
    public void activateLot(Long id, DateEndOfTradingDto dateEndOfTradingDto) {
        Lot activatedLot = lotDao.getById(id);
        if (activatedLot == null) {
            throw new LotNotFoundException(id + "");
        }
        checkAccessRights(activatedLot);
        if (activatedLot.getStatus().equals(LotStatus.PAUSED)
                || activatedLot.getStatus().equals(LotStatus.NEW)
                || activatedLot.getStatus().equals(LotStatus.SOLD_BUT_NOT_PAID)) {
            activatedLot.setStatus(LotStatus.ACTIVE);
            activatedLot.setDateOfStartTrading(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
            activatedLot.setDateOfEndTrading(dateEndOfTradingDto.getDateOfEndTrading());
            activatedLot.setLastDayOfPay(dateEndOfTradingDto.getDateOfEndTrading().plusSeconds(20));//для тестов установили 20 sec
            lotDao.update(activatedLot);
        } else {
            throw new LotCanNotBeActivatedException(activatedLot.getStatus() + "");
        }
    }

    private void checkAccessRights(Lot activatedLot) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl details = (UserDetailsImpl) authentication.getDetails();
        if (!details.getAuthorities().stream().findFirst().get().getAuthority().equals("ROLE_ADMIN")) {
            if (!details.getId().equals(activatedLot.getUser().getId())) {
                throw new AuthorizationServiceException("User with id: " + details.getId() + " doesn't have access to this lot!");
            }
        }
    }

    @Override
    public void changeLotStatus(Long id, LotStatus lotStatus) {//нужен ли?
        Lot lot = lotDao.getById(id);
        lot.setStatus(lotStatus);
        lotDao.update(lot);
    }

}
