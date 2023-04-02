package com.liberty52.auth.global.oauth2;

import com.liberty52.auth.global.jwt.JwtService;
import com.liberty52.auth.global.utils.Tokens;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
//@Transactional
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

  private final JwtService jwtService;
  @Value("${frontend.url}")
  private String frontendURL;
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    log.debug("OAuth2 Login 성공!");
    try {
      CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
      Tokens tokens = loginSuccess(response, oAuth2User);// 로그인에 성공한 경우 access, refresh 토큰 생성
      response.sendRedirect(String.format(frontendURL,tokens.getAccessToken(),tokens.getRefreshToken()));
    } catch (Exception e) {
      throw e;
    }

  }
  private Tokens loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
    String accessToken = jwtService.createAccessToken(oAuth2User.getId());
    String refreshToken = jwtService.createRefreshToken();
    response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
    response.addHeader(jwtService.getRefreshHeader(), "Bearer " + refreshToken);

    jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
    jwtService.updateRefreshToken(oAuth2User.getEmail(), refreshToken);

    return new Tokens(accessToken,refreshToken);
  }
}

