package com.liberty52.auth.global.exception.external;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuthErrorCode implements ErrorCode{
    AUTH_NOT_FOUND(HttpStatus.UNAUTHORIZED,"등록되지 않은 계정입니다."),

    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,"이미 존재하는 이메일입니다."),

    INVALID_SOCIAL_LOGIN_CODE_ACCESSED(HttpStatus.BAD_REQUEST,"불가능한 소셜 로그인 코드가 도달했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "현재 시스템에 오류가 발생하였습니다. 다시 시도해주세요.\n자세한 문의는 고객센터에 문의해주세요."),

    ;

    private final HttpStatus httpStatus;
    private final String errorMessage;

    private final String errorCode = "A-" + "0".repeat(Math.max(4-String.valueOf(this.ordinal() + 1).length(), 0)) + (this.ordinal() + 1);

    AuthErrorCode(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public String getErrorName() {
        return this.name();
    }
}