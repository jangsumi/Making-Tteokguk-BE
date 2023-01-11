package com.ricecakesoup.service.ingredient.dto.response;

import com.ricecakesoup.domain.Ingredient.Ingredient;
import lombok.Getter;

@Getter
public class IngredientResDto {
    private int type;
    private String title;
    private String content;

    public static IngredientResDto of(Ingredient ingredient) {
        return new IngredientResDto(ingredient);
    }

    private IngredientResDto(final Ingredient ingredient) {
        this.type = ingredient.getType();
        this.title = ingredient.getTitle();
        this.content = ingredient.getContent();
    }
}
