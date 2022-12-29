package com.ricecakesoup.domain.refrigerator;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefrigeratorRepository  extends JpaRepository<Refrigerator, Long>{
    Refrigerator findByKakaoId(String kakaoId);

    Refrigerator findByLink(String link);


}
