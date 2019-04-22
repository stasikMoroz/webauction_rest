package com.vironit.businessauction.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString(includeFieldNames = false)
@MappedSuperclass
public abstract class BaseEntity {
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @Column(name = "updated_date_time")
    private LocalDateTime updatedDateTime;
}
