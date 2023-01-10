package com.ricecakesoup.controller.ricecakesoup;

import com.ricecakesoup.controller.ricecakesoup.dto.request.SoupMakeReqDto;
import com.ricecakesoup.service.ingredient.dto.response.IngredientResDto;
import com.ricecakesoup.service.ricecakesoup.RiceCakeSoupService;
import com.ricecakesoup.service.ricecakesoup.dto.response.SoupResDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/rice-cake-soup")
@RequiredArgsConstructor
public class RiceCakeSoupController {

    private final RiceCakeSoupService riceCakeSoupService;

    @PostMapping("{fridgeId}")
    @ApiOperation("떡국 만들기")
    public List<IngredientResDto> makeSoup(@RequestBody final SoupMakeReqDto soupMakeReqDto, @PathVariable final Long fridgeId) {
        return riceCakeSoupService.makeSoup(soupMakeReqDto, fridgeId);
    }

    @GetMapping("{fridgeId}")
    @ApiOperation("내가 만든 떡국")
    public List<SoupResDto> getSoup(@PathVariable final Long fridgeId) {
        try {
            return riceCakeSoupService.getSoup(fridgeId);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}
