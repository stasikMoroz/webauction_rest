package com.vironit.businessauction.service;

import com.vironit.businessauction.dto.*;
import com.vironit.businessauction.entity.Lot;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.entity.UserStatus;

import java.util.List;

public interface UserService {
    UserDto findUserById(Long id);
    UserDto addUser(UserDto userDto);
    void updateUser(Long id, UserDto userDto);
    List<UserDto> getListOfUsers();
    List<UserDto> getUsersByStatus(UserStatusDto userStatusDto);
    UserDto userIsPresent(UserAuthenticationDto userAuthenticationDto);
    void deleteUser(Long id);
    TradeHistory getUserHistory(Long id);
    void topUpWallet(Long id, WalletUserDto walletUserDto);
    void payForTheLot(Long userId, Long lotId);
}
