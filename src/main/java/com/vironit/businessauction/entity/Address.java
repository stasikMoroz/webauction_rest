package com.vironit.businessauction.entity;

import lombok.*;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = false)
@Embeddable
//@Entity
//@Table(name = "address", schema = "webauction_storage", catalog = "webauction_db")
public class Address {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "room")
    private String room;
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private User user;
}
