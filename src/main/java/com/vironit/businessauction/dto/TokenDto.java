package com.vironit.businessauction.dto;

import com.vironit.businessauction.entity.Token;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class TokenDto {

    private String value;

    public TokenDto(Token token) {
        this.value = token.getValue();
    }
}
