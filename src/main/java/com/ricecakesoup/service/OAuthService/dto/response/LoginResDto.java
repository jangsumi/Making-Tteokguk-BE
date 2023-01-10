package com.ricecakesoup.service.OAuthService.dto.response;

import lombok.Getter;

@Getter
public class LoginResDto {
    private String kakaoId;

    public static LoginResDto of(final String kakaoId) {
        return new LoginResDto(kakaoId);
    }

    private LoginResDto (final String kakaoId) {
        this.kakaoId = kakaoId;
    }
}
