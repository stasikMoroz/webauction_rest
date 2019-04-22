package com.vironit.businessauction.entity;

import com.vironit.businessauction.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@ToString(callSuper = true, includeFieldNames = false)
@EqualsAndHashCode(callSuper = true)
@Table(name = "user", schema = "webauction_storage", catalog = "webauction_db")
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "wallet")
    private double wallet;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus userStatus;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Embedded
    private Address address;
    @Embedded
    private Passport passport;

    public void initUser(UserDto userDto) {
        this.name = userDto.getName();
        this.surname = userDto.getSurname();
        this.login = userDto.getLogin();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.phoneNumber = userDto.getPhoneNumber();
        this.wallet = userDto.getWallet();
        this.address = userDto.getAddress();
        this.passport = userDto.getPassport();
    }
}











