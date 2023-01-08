package com.ricecakesoup.domain.refrigerator;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Refrigerator {
    public Refrigerator() {}

    private Refrigerator(final boolean isSecret, final String link, final String kakaoId, final String nickname, final int color) {
        this.isSecret = isSecret;
        this.link = link;
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.color = color;
        this.unlockRCS = 256;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refrigerator_id")
    private Long id;

    @Column
    private int color;

    @Column
    private boolean isSecret;

    @Column
    private String link;

    @Column
    private String kakaoId;

    @Column
    private String nickname;

    @Column
    private int unlockRCS;

    public static Refrigerator newInstance(final boolean isSecret, final String link, final String kakaoId, final String nickname, final int color) {
        return new Refrigerator(isSecret, link, kakaoId, nickname, color);
    }

    public void setUnlockRCS(int type) {
        unlockRCS = unlockRCS | (int) Math.pow(2, type);
    }
}
