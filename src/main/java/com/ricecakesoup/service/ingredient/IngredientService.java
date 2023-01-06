package com.ricecakesoup.service.ingredient;

import com.ricecakesoup.controller.ingredient.dto.request.IngredientCreateReqDto;
import com.ricecakesoup.domain.Ingredient.Ingredient;
import com.ricecakesoup.domain.Ingredient.IngredientRepository;
import com.ricecakesoup.domain.refrigerator.Refrigerator;
import com.ricecakesoup.domain.refrigerator.RefrigeratorRepository;
import com.ricecakesoup.service.ingredient.dto.response.IngredientNotUsedResDto;
import com.ricecakesoup.service.ingredient.dto.response.IngredientResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final RefrigeratorRepository refrigeratorRepository;

    public void createIngredient(final Long fridgeId, final IngredientCreateReqDto ingredientCreateReqDto) {
        Refrigerator refrigerator = refrigeratorRepository.findById(fridgeId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 냉장고 id 입니다."));
        int type = ingredientCreateReqDto.getType();
        String title = ingredientCreateReqDto.getTitle();
        String content = ingredientCreateReqDto.getContent();
        Ingredient ingredient = Ingredient.newInstance(refrigerator, title, content, type, false);
        ingredientRepository.save(ingredient);
    }

    public List<IngredientResDto> getIngredient(final boolean isUsed, final Long fridgeId) {
        List<Ingredient> ingredientList;
        if (isUsed)
            ingredientList = ingredientRepository.findByUsedTrueAndRefrigeratorIdOrderByCreatedAtDesc(fridgeId);
        else
            ingredientList = ingredientRepository.findByUsedFalseAndRefrigeratorId(fridgeId);

        List<IngredientResDto> ingredientResDtoList = ingredientList.stream()
                                                        .map(IngredientResDto::of)
                                                        .collect(Collectors.toList());
        if (ingredientResDtoList.isEmpty())
            throw new RuntimeException();
        return ingredientResDtoList;
    }

//    public IngredientNotUsedResDto getNotUsedIngredient(final Long fridgeId) {
//        Refrigerator refrigerator = refrigeratorRepository.findById(fridgeId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 냉장고 id 입니다."));
//
//    }
}
