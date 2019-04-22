package com.vironit.businessauction.service.impl;

import com.vironit.businessauction.dao.BidDao;
import com.vironit.businessauction.dao.FeedbackDao;
import com.vironit.businessauction.dao.LotDao;
import com.vironit.businessauction.dao.UserDao;
import com.vironit.businessauction.dto.*;
import com.vironit.businessauction.entity.*;
import com.vironit.businessauction.exception.*;
import com.vironit.businessauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private BidDao bidDao;
    private LotDao lotDao;
    private FeedbackDao feedbackDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

     @Autowired
    public UserServiceImpl(UserDao userDao, BidDao bidDao, LotDao lotDao, FeedbackDao feedbackDao) {
        this.userDao = userDao;
        this.bidDao = bidDao;
        this.lotDao = lotDao;
        this.feedbackDao = feedbackDao;
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userDao.getById(id);
        if (user == null) {
            throw new UserNotFoundException(id + "");
        }
        return new UserDto(user);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
       if (userDao.checkUserLogin(userDto.getLogin())) {
           throw new LoginAlredyExistException("User with login " + userDto.getLogin());
       } else {
           String encodedPassword = passwordEncoder.encode(userDto.getPassword());
           userDto.setPassword(encodedPassword);
           User user = new User();
           user.initUser(userDto);
           user.setUserStatus(UserStatus.ACTIVE);
           user.setRole(Role.ROLE_USER);
           user.setCreatedDateTime(LocalDateTime.now());
           return new UserDto(userDao.save(user));
       }
    }

    @Override
    public void updateUser(Long id, UserDto userDto) {
        User updatedUser = userDao.getById(id);
        if (updatedUser == null) {
            throw new UserNotFoundException(id + "");
        }
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        updatedUser.setUpdatedDateTime(LocalDateTime.now());
        updatedUser.setName(userDto.getName());
        updatedUser.setSurname(userDto.getSurname());
        updatedUser.setLogin(userDto.getLogin());
        updatedUser.setPassword(encodedPassword);
        updatedUser.setEmail(userDto.getEmail());
        updatedUser.setPhoneNumber(userDto.getPhoneNumber());
        updatedUser.setPassport(userDto.getPassport());
        updatedUser.setAddress(userDto.getAddress());

        userDao.update(updatedUser);
    }

    @Override
    public List<UserDto> getListOfUsers() {
        return userDao.getAll().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersByStatus(UserStatusDto userStatusDto) {
        return userDao.getUsersByStatus(userStatusDto.getUserStatus()).stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto userIsPresent(UserAuthenticationDto userAuthenticationDto) {
        Optional<User> userOptional = userDao.userAuthentication(userAuthenticationDto.getLogin(), userAuthenticationDto.getPassword());
        User user = userOptional.orElseThrow(LoginOrPasswordIncorrectException::new);
        return new UserDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userDao.getById(id);
        if (user == null) {
            throw new UserNotFoundException(id + "");
        }
        feedbackDao.deleteFeedbackByUserId(user.getId());
        bidDao.deleteBidByUserId(user.getId());
        lotDao.deleteLotByUserId(user.getId());
        userDao.delete(id);
    }

    @Override
    public TradeHistory getUserHistory(Long id) {
        TradeHistory tradeHistory = new TradeHistory();
        User user = userDao.getById(id);
        if (user == null) {
            throw new UserNotFoundException(id + "");
        }
        tradeHistory.setUserId(user.getId());
        tradeHistory.setBidList(bidDao.getBidsByUser(user).stream()
            .map(BidDto::new)
            .collect(Collectors.toList()));
        tradeHistory.setLotList(lotDao.getAllUsersLots(user).stream()
            .map(LotDto::new)
            .collect(Collectors.toList()));

        return tradeHistory;
    }

    @Override
    public void topUpWallet(Long id, WalletUserDto walletUserDto) {
        User user = userDao.getById(id);
        double sum = walletUserDto.getSum();
        if (user == null) {
            throw new UserNotFoundException(id + "");
        }
        if (sum >= 0) {
            user.setWallet(user.getWallet() + sum);
            userDao.update(user);
        } else {
            throw new WalletException();
        }
    }

    @Override
    public void payForTheLot(Long userId, Long lotId) {
        User userFromDb = userDao.getById(userId);
        if (userFromDb == null) {
            throw new UserNotFoundException(userId + "");
        }
        Lot lotFromDb = lotDao.getById(lotId);
        if (lotFromDb == null) {
            throw new LotNotFoundException(lotId + "");
        }
        Long userWhoWonLotId = lotFromDb.getUserWhoWonLotId();

        if (lotFromDb.getStatus().equals(LotStatus.SOLD_BUT_NOT_PAID) && Objects.equals(userWhoWonLotId, userId)) {
            if (userFromDb.getWallet() < lotFromDb.getCurrentPrice()) {
                throw new UserNotHaveEnoughMoneyPayForLotException(userFromDb.getId() + "");
            } else {
                double lotPrice = lotFromDb.getCurrentPrice();
                userFromDb.setWallet(userFromDb.getWallet() - lotPrice);
                User userWhosells = userDao.getById(lotFromDb.getUser().getId());
                userWhosells.setWallet(userWhosells.getWallet() + lotPrice);
                userDao.update(userWhosells);

                lotFromDb.setStatus(LotStatus.SOLD_AND_PAID);
                userDao.update(userFromDb);
                lotDao.update(lotFromDb);
            }
        } else {
            throw new ThisLotNotPayableException(lotId + "");
        }
    }

}
