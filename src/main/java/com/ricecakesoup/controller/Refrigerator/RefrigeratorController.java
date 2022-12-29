package com.ricecakesoup.controller.Refrigerator;

import com.ricecakesoup.controller.Refrigerator.dto.request.RefrigeratorInitReqDto;
import com.ricecakesoup.service.refrigerator.RefrigeratorService;
import com.ricecakesoup.service.refrigerator.dto.response.RefrigeratorResDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fridge")
@RequiredArgsConstructor
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;

    @ApiOperation("냉장고 생성")
    @PostMapping("/init")
    public void initFridge(@RequestBody final RefrigeratorInitReqDto refrigeratorInitReqDto) {
        refrigeratorService.initRefrigerator(refrigeratorInitReqDto);
    }

    @ApiOperation("로그인 후 아이디로 냉장고 정보 가져오기")
    @GetMapping("/{kakaoId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public RefrigeratorResDto getFridgeById(@PathVariable final String kakaoId) {
        return refrigeratorService.getFridgeById(kakaoId);
    }

    @ApiOperation("링크로 냉장고 정보 가져오기")
    @GetMapping("/{link}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public RefrigeratorResDto getFridgeByLink(@PathVariable final String link) {
        return refrigeratorService.getFridgeByLink(link);
    }
}
