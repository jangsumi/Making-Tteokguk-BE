package com.ricecakesoup.domain.ricecakesoup;

import com.ricecakesoup.domain.refrigerator.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiceCakeSoupRepository extends JpaRepository<RiceCakeSoup, Long> {

    List<RiceCakeSoup> findByRefrigerator(Refrigerator refrigerator);

}
