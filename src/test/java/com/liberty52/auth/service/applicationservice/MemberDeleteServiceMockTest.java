package com.liberty52.auth.service.applicationservice;

import static com.liberty52.auth.service.utils.MockConstants.MOCK_USER_EMAIL;
import static com.liberty52.auth.service.utils.MockFactory.createMockAuth;
import static org.assertj.core.api.Assertions.*;

import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.fake.FakeAuthRepository;
import com.liberty52.auth.service.fake.FakeMemberDeleteService;
import com.liberty52.auth.service.repository.AuthRepository;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

class MemberDeleteServiceMockTest {

    AuthRepository authRepository;
    MemberDeleteService memberDeleteService;

    String mockUserId;

    @BeforeEach
    void setMockUserData(){
        authRepository = new FakeAuthRepository();
        memberDeleteService = new FakeMemberDeleteService(authRepository);

        // save Auth Entity
        Auth auth = createMockAuth();
        mockUserId = authRepository.save(auth).getId();
    }
    // Controller에서 UserId를 받았을 때 삭제
    @Test
    void deleteByUserId  () throws Exception{
        //given
        memberDeleteService.deleteMemberByUserId(mockUserId);
        //when
        Optional<Auth> result = authRepository.findById(mockUserId);
        //then
        assertThat(result).isNotPresent();
    }
}