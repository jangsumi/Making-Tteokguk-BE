package com.ricecakesoup.domain.Ingredient;

import com.ricecakesoup.domain.refrigerator.Refrigerator;

import javax.persistence.*;

public class Ingredient {
    public Ingredient() {}

    private Ingredient(Refrigerator refrigerator, String title, String content, int type, boolean isUsed) {
        this.refrigerator = refrigerator;
        this.title = title;
        this.content = content;
        this.type = type;
        this.isUsed = isUsed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "refrigerator_id")
    private Refrigerator refrigerator;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private int type;

    @Column
    private boolean isUsed;

    private static Ingredient newInstance(Refrigerator refrigerator, String title, String content, int type, boolean isUsed) {
        return new Ingredient(refrigerator, title, content, type, isUsed);
    }
}
