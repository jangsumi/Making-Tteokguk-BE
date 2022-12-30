package com.ricecakesoup.service.ricecakesoup.dto.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SoupResDto {
    private List<Integer> ingredientList = new ArrayList<>();

    public void addIngredient(int typeNum) {
        ingredientList.add(typeNum);
    }
}
