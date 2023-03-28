package com.liberty52.auth.service.conotroller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
