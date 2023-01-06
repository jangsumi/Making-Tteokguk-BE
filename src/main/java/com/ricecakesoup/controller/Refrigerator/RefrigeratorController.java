package com.ricecakesoup.controller.Refrigerator;

import com.ricecakesoup.controller.Refrigerator.dto.request.RefrigeratorInitReqDto;
import com.ricecakesoup.service.refrigerator.RefrigeratorService;
import com.ricecakesoup.service.refrigerator.dto.response.RefrigeratorResDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/fridge")
@RequiredArgsConstructor
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;

    @ApiOperation("냉장고 생성")
    @PostMapping("/init")
    public RefrigeratorResDto initFridge(@RequestBody final RefrigeratorInitReqDto refrigeratorInitReqDto, @RequestParam final boolean isSecret) {
        return refrigeratorService.initRefrigerator(refrigeratorInitReqDto, isSecret);
    }

    @ApiOperation("로그인 후 아이디로 냉장고 정보 가져오기")
    @GetMapping
    public RefrigeratorResDto getFridgeById(@RequestParam final String kakaoId) {
        try {
            return refrigeratorService.getFridgeById(kakaoId);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation("링크로 냉장고 정보 가져오기")
    @GetMapping("/{link}")
    public RefrigeratorResDto getFridgeByLink(@PathVariable final String link) {
        try {
            return refrigeratorService.getFridgeByLink(link);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}
