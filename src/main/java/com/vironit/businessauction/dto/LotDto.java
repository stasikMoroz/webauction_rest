package com.vironit.businessauction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.vironit.businessauction.entity.Category;
import com.vironit.businessauction.entity.Lot;
import com.vironit.businessauction.entity.LotStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class LotDto {
    private Long id;
    private Double startPrice;
    private String description;
    private Category category;
    private LotStatus lotStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateOfEndTrading;
    private Long userId;

    public LotDto(Lot lot) {
        this.id = lot.getId();
        this.startPrice = lot.getStartPrice();
        this.description = lot.getDescription();
        this.category = lot.getCategory();
        this.lotStatus = lot.getStatus();
        this.dateOfEndTrading = lot.getDateOfEndTrading();
        this.userId = lot.getUser().getId();
    }
}
