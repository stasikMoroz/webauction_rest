package com.vironit.businessauction.dto;

import com.vironit.businessauction.entity.Address;
import com.vironit.businessauction.entity.Passport;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.entity.UserStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String phoneNumber;
    private UserStatus userStatus;
    private double wallet;
    private Passport passport;
    private Address address;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.userStatus = user.getUserStatus();
        this.wallet = user.getWallet();
        this.passport = user.getPassport();
        this.address = user.getAddress();
    }
}
