package com.example.demo.Dto;

import lombok.Data;
import lombok.Getter;


@Data
public class AuthenticationResponseDto {

    private final String accessToken;

    private final String refreshToken;


    public AuthenticationResponseDto(String token, String refreshToken) {
        this.accessToken = token;
        this.refreshToken = refreshToken;
    }

}
