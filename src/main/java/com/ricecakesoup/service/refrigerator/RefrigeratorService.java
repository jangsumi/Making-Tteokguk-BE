package com.ricecakesoup.service.refrigerator;

import com.ricecakesoup.controller.Refrigerator.dto.request.RefrigeratorInitReqDto;
import com.ricecakesoup.domain.refrigerator.Refrigerator;
import com.ricecakesoup.domain.refrigerator.RefrigeratorRepository;
import com.ricecakesoup.service.refrigerator.dto.response.RefrigeratorResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefrigeratorService {
    private final RefrigeratorRepository refrigeratorRepository;
    public void initRefrigerator(final RefrigeratorInitReqDto refrigeratorInitReqDto) {
        boolean isSecret = refrigeratorInitReqDto.isSecret();
        String kakaoId = refrigeratorInitReqDto.getKakaoId();
        String nickname = refrigeratorInitReqDto.getNickname();
        int color = refrigeratorInitReqDto.getColor();
        // TODO link parameter 교체 필요
        Refrigerator refrigerator = Refrigerator.newInstance(isSecret, kakaoId, kakaoId, nickname, color);
        refrigeratorRepository.save(refrigerator);
    }

    public RefrigeratorResDto getFridgeById(final String kakaoId) {
        Refrigerator refrigerator = refrigeratorRepository.findByKakaoId(kakaoId);
        return RefrigeratorResDto.of(refrigerator);
    }

    public RefrigeratorResDto getFridgeByLink(final String link) {
        Refrigerator refrigerator = refrigeratorRepository.findByLink(link);
        return RefrigeratorResDto.of(refrigerator);
    }
}
