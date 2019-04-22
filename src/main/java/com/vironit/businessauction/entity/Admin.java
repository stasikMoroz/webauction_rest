package com.vironit.businessauction.entity;

import com.vironit.businessauction.dto.AdminDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "admin", schema = "webauction_storage", catalog = "webauction_db")
@NoArgsConstructor
public class Admin extends BaseEntity {//TODO удалить
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    public void initAdmin(AdminDto adminDto) {
        this.name = adminDto.getName();
        this.login = adminDto.getLogin();
        this.password = adminDto.getPassword();
    }
}
