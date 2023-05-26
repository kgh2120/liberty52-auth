package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.LoginService;
import com.liberty52.auth.service.controller.dto.EmailLoginRequestDto;
import com.liberty52.auth.service.controller.dto.LoginResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public LoginResponseDto login(@Validated @RequestBody EmailLoginRequestDto dto, HttpServletResponse response) {
        return loginService.login(dto, response);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/refresh")
    public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, @RequestHeader("LB-RefreshToken") String refreshToken){
        loginService.checkRefreshTokenAndReIssueAccessToken(response,refreshToken);
    }
}
