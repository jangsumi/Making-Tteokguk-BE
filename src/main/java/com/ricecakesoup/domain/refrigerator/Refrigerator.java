package com.ricecakesoup.domain.refrigerator;

import lombok.Getter;

import javax.persistence.*;

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

    public static Refrigerator newInstance(final boolean isSecret, final String link, final String kakaoId, final String nickname, final int color) {
        return new Refrigerator(isSecret, link, kakaoId, nickname, color);
    }
}
