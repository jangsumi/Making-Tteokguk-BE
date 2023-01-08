package com.ricecakesoup.controller.ingredient;

import com.ricecakesoup.controller.ingredient.dto.request.IngredientCreateReqDto;
import com.ricecakesoup.service.ingredient.IngredientService;
import com.ricecakesoup.service.ingredient.dto.response.IngredientNotUsedResDto;
import com.ricecakesoup.service.ingredient.dto.response.IngredientResDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @ApiOperation("떡국 재료 선물")
    @PostMapping("/create/{fridgeId}")
    public void createIngredient(@PathVariable final Long fridgeId, @RequestBody final IngredientCreateReqDto ingredientCreateReqDto) {
        ingredientService.createIngredient(fridgeId, ingredientCreateReqDto);
    }

    @ApiOperation("내가 받은 덕담")
    @GetMapping("/{fridgeId}")
    public List<IngredientResDto> getIngredient(@RequestParam final boolean isUsed, @PathVariable final Long fridgeId) {
        try {
            return ingredientService.getIngredient(isUsed, fridgeId);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation("사용하지 않은 재료 개수 가져오기")
    @GetMapping("/{fridgeId}/not-used")
    public List<Integer> getNotUsedIngredient(@PathVariable final Long fridgeId) {
        try {
            return ingredientService.getNotUsedIngredient(fridgeId);
        }catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}
