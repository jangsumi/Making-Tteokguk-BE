package com.ricecakesoup.controller.ingredient.dto.request;

import lombok.Getter;

@Getter
public class IngredientCreateReqDto {
    private int type;
    private String title;
    private String content;
}
