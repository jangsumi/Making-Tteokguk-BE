package com.ricecakesoup.service.refrigerator.dto.response;

import com.ricecakesoup.domain.refrigerator.Refrigerator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RefrigeratorResDto {
    private Long id;
    private int color;
    private boolean isSecret;
    private String link;
    private String nickname;
    private String kakaoId;

    private List<Boolean> unlockedRCS;

    public static RefrigeratorResDto of(final Refrigerator refrigerator) {
        return new RefrigeratorResDto(refrigerator);
    }

    private RefrigeratorResDto(final Refrigerator refrigerator){
        this.id = refrigerator.getId();
        this.color = refrigerator.getColor();
        this.isSecret = refrigerator.isSecret();
        this.link = refrigerator.getLink();
        this.nickname = refrigerator.getNickname();
        this.kakaoId = refrigerator.getKakaoId();
        unlockedRCS = binToList(refrigerator.getUnlockRCS());
    }

    private List<Boolean> binToList(int unlockRCS) {
        int binRcs = Integer.parseInt(Integer.toBinaryString(unlockRCS));
        List<Boolean> typeList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            int isUnlock = binRcs % 10;
            binRcs /= 10;
            typeList.add(isUnlock == 1);
        }
        System.out.println(typeList.toString());

        return typeList;
    }
}
