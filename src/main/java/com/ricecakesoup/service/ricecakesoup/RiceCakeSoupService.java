package com.ricecakesoup.service.ricecakesoup;

import com.ricecakesoup.controller.ricecakesoup.dto.request.SoupMakeReqDto;
import com.ricecakesoup.domain.Ingredient.Ingredient;
import com.ricecakesoup.domain.Ingredient.IngredientRepository;
import com.ricecakesoup.domain.refrigerator.Refrigerator;
import com.ricecakesoup.domain.refrigerator.RefrigeratorRepository;
import com.ricecakesoup.domain.ricecakesoup.RiceCakeSoup;
import com.ricecakesoup.domain.ricecakesoup.RiceCakeSoupRepository;
import com.ricecakesoup.service.ricecakesoup.dto.response.SoupResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RiceCakeSoupService {
    private final RiceCakeSoupRepository riceCakeSoupRepository;
    private final RefrigeratorRepository refrigeratorRepository;

    private final IngredientRepository ingredientRepository;

    public void makeSoup(final SoupMakeReqDto soupMakeReqDto, final Long fridgeId) {
        List<Integer> ingredientList = soupMakeReqDto.getIngredientList();
        Refrigerator refrigerator = refrigeratorRepository.findById(fridgeId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 내장고 id 입니다."));
        RiceCakeSoup riceCakeSoup = RiceCakeSoup.newInstance(refrigerator);
        riceCakeSoupRepository.save(riceCakeSoup);
        for (int typeNum : ingredientList) {
            ingredientToSoup(refrigerator, riceCakeSoup, typeNum);
        }
        refrigerator.setUnlockRCS(soupMakeReqDto.getSoupType());
        refrigeratorRepository.save(refrigerator);
    }

    private void ingredientToSoup (Refrigerator refrigerator, RiceCakeSoup riceCakeSoup, int type) {
        Ingredient ingredient = ingredientRepository.findFirstByTypeAndUsedFalseAndRefrigeratorOrderByCreatedAtAsc(type, refrigerator);
        ingredient.setRiceCakeSoup(riceCakeSoup);
        ingredient.setUsed(true);
        ingredientRepository.save(ingredient);
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
