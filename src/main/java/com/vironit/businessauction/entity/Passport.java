package com.vironit.businessauction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = false)
@Embeddable
//@Table(name = "passport", schema = "webauction_storage", catalog = "webauction_db")
//@Entity
public class Passport {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "issued_by")
    private String issuedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "date_issued")
    private LocalDate dateIssued;
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private User user;
}
