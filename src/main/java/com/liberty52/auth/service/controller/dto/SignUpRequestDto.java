package com.liberty52.auth.service.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private String phoneNumber;

    @NotBlank
    private String name;

    private String recommender;

}
