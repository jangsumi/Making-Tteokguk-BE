package com.ricecakesoup.controller.Refrigerator.dto.request;

import lombok.Getter;
@Getter
public class RefrigeratorInitReqDto {
    private boolean isSecret;
    private String nickname;
    private String kakaoId;
    private int color;
}
