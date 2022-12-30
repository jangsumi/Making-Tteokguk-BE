package com.ricecakesoup.domain.Ingredient;

import com.ricecakesoup.domain.common.BaseTimeEntity;
import com.ricecakesoup.domain.refrigerator.Refrigerator;
import com.ricecakesoup.domain.ricecakesoup.RiceCakeSoup;
import lombok.Getter;

import javax.persistence.*;
@Entity
@Getter
public class Ingredient extends BaseTimeEntity {
    public Ingredient() {}

    private Ingredient(Refrigerator refrigerator, String title, String content, int type, boolean isUsed) {
        this.refrigerator = refrigerator;
        this.title = title;
        this.content = content;
        this.type = type;
        this.used = isUsed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "refrigerator_id")
    private Refrigerator refrigerator;

    @ManyToOne
    @JoinColumn(name = "rice_cake_soup_id")
    private RiceCakeSoup riceCakeSoup;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private int type;

    @Column
    private boolean used;

    public static Ingredient newInstance(Refrigerator refrigerator, String title, String content, int type, boolean isUsed) {
        return new Ingredient(refrigerator, title, content, type, isUsed);
    }

    public void setRiceCakeSoup(RiceCakeSoup riceCakeSoup) {
        this.riceCakeSoup = riceCakeSoup;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public int ingredientToType() {
        return type;
    }
}
