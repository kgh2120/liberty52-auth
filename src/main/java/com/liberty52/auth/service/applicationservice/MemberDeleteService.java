package com.liberty52.auth.service.applicationservice;

public interface MemberDeleteService {

    void deleteMemberByUserId(String userId);

    void deleteMemberByEmail(String email);

    void deleteMemberWithToken();
}
