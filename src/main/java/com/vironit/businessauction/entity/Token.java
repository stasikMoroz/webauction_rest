package com.vironit.businessauction.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "token", catalog = "webauction_db", schema = "webauction_storage")
public class Token extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "value")
    private String value;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
