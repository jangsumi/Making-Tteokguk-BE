package com.ricecakesoup.domain.refrigerator;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefrigeratorRepository  extends JpaRepository<Refrigerator, Long>{
    Optional<Refrigerator> findByKakaoId(String kakaoId);

    Refrigerator findByLink(String link);


}
