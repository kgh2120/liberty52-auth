package com.liberty52.auth.global.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tokens {

    private String accessToken;
    private String refreshToken;

}
