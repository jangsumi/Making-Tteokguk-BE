package com.ricecakesoup.service.ricecakesoup;

import com.ricecakesoup.controller.ricecakesoup.dto.request.SoupMakeReqDto;
import com.ricecakesoup.domain.Ingredient.Ingredient;
import com.ricecakesoup.domain.Ingredient.IngredientRepository;
import com.ricecakesoup.domain.refrigerator.Refrigerator;
import com.ricecakesoup.domain.refrigerator.RefrigeratorRepository;
import com.ricecakesoup.domain.ricecakesoup.RiceCakeSoup;
import com.ricecakesoup.domain.ricecakesoup.RiceCakeSoupRepository;
import com.ricecakesoup.service.ingredient.dto.response.IngredientResDto;
import com.ricecakesoup.service.ricecakesoup.dto.response.SoupResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RiceCakeSoupService {
    private final RiceCakeSoupRepository riceCakeSoupRepository;
    private final RefrigeratorRepository refrigeratorRepository;

    private final IngredientRepository ingredientRepository;

    public List<IngredientResDto> makeSoup(final SoupMakeReqDto soupMakeReqDto, final Long fridgeId) {
        List<Integer> ingredientTypeList = soupMakeReqDto.getIngredientList();
        Refrigerator refrigerator = refrigeratorRepository.findById(fridgeId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 내장고 id 입니다."));
        RiceCakeSoup riceCakeSoup = RiceCakeSoup.newInstance(refrigerator);
        riceCakeSoupRepository.save(riceCakeSoup);
        List<Ingredient> ingredientList = new ArrayList<>();
        for (int typeNum : ingredientTypeList) {
            ingredientList.add(ingredientToSoup(refrigerator, riceCakeSoup, typeNum));
        }

        System.out.println("err1");
        List<IngredientResDto> ingredientResDtoList = ingredientList.stream()
                .map(IngredientResDto::of)
                .collect(Collectors.toList());
        System.out.println("err2");

        refrigerator.setUnlockRCS(soupMakeReqDto.getSoupType());
        refrigeratorRepository.save(refrigerator);

        return ingredientResDtoList;
    }

    private Ingredient ingredientToSoup (Refrigerator refrigerator, RiceCakeSoup riceCakeSoup, int type) {
        Ingredient ingredient = ingredientRepository.findFirstByTypeAndUsedFalseAndRefrigeratorOrderByCreatedAtAsc(type, refrigerator);
        ingredient.setRiceCakeSoup(riceCakeSoup);
        ingredient.setUsed(true);
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    public List<SoupResDto> getSoup(Long fridgeId) {
        Refrigerator fridge = refrigeratorRepository.findById(fridgeId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 내장고 id 입니다."));
        List<RiceCakeSoup> riceCakeSoups = riceCakeSoupRepository.findByRefrigerator(fridge);
        if (riceCakeSoups.isEmpty())
            throw new RuntimeException();
        List<SoupResDto> result = new ArrayList<>();
        for(RiceCakeSoup soup : riceCakeSoups) {
            List<Ingredient> ingredients = ingredientRepository.findByRiceCakeSoup(soup);
            SoupResDto soupResDto = new SoupResDto();
            for(Ingredient ingredient : ingredients) {
                soupResDto.addIngredient(ingredient.getType());
            }
            result.add(soupResDto);
        }
        return result;
    }
}
