package com.vironit.businessauction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponseDto {

    private int httpStatus;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponseDto(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        timestamp = LocalDateTime.now();
    }
}
