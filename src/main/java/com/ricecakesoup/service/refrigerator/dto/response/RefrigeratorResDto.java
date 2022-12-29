package com.ricecakesoup.service.refrigerator.dto.response;

import com.ricecakesoup.domain.refrigerator.Refrigerator;
import lombok.Getter;

@Getter
public class RefrigeratorResDto {
    private Long id;
    private int color;
    private boolean isSecret;
    private String link;
    private String nickname;
    private String kakaoId;

    public static RefrigeratorResDto of(final Refrigerator refrigerator) {
        return new RefrigeratorResDto(refrigerator);
    }

    private RefrigeratorResDto(final Refrigerator refrigerator) {
        this.id = refrigerator.getId();
        this.color = refrigerator.getColor();
        this.isSecret = refrigerator.isSecret();
        this.link = refrigerator.getLink();
        this.nickname = refrigerator.getNickname();
        this.kakaoId = refrigerator.getKakaoId();
    }
}
