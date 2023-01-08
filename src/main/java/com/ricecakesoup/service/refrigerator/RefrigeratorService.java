package com.ricecakesoup.service.refrigerator;

import com.ricecakesoup.controller.Refrigerator.dto.request.RefrigeratorInitReqDto;
import com.ricecakesoup.domain.refrigerator.Refrigerator;
import com.ricecakesoup.domain.refrigerator.RefrigeratorRepository;
import com.ricecakesoup.service.refrigerator.dto.response.RefrigeratorResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefrigeratorService {
    private final RefrigeratorRepository refrigeratorRepository;
    public RefrigeratorResDto initRefrigerator(final RefrigeratorInitReqDto refrigeratorInitReqDto, final boolean isSecret) {
        String kakaoId = refrigeratorInitReqDto.getKakaoId();
        String nickname = refrigeratorInitReqDto.getNickname();
        int color = refrigeratorInitReqDto.getColor();
        // TODO link parameter 교체 필요
        Refrigerator refrigerator = Refrigerator.newInstance(isSecret, kakaoId, kakaoId, nickname, color);
        refrigeratorRepository.save(refrigerator);
        return RefrigeratorResDto.of(refrigerator);
    }

    public RefrigeratorResDto getFridgeById(final String kakaoId) {

        Optional<Refrigerator> refrigerator = refrigeratorRepository.findByKakaoId(kakaoId);            System.out.println();

        return RefrigeratorResDto.of(refrigerator.get());
    }

    public RefrigeratorResDto getFridgeByLink(final String link) {
        Refrigerator refrigerator = refrigeratorRepository.findByLink(link);
        return RefrigeratorResDto.of(refrigerator);
    }
}
