package com.vironit.businessauction.dto;

import com.vironit.businessauction.entity.Admin;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class AdminDto {
    private Long id;
    private String name;
    private String login;
    private String password;

    public AdminDto(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.login = admin.getLogin();
        this.password = admin.getPassword();
    }
}
