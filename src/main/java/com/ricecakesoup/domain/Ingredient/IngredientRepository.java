package com.ricecakesoup.domain.Ingredient;

import com.ricecakesoup.domain.refrigerator.Refrigerator;
import com.ricecakesoup.domain.ricecakesoup.RiceCakeSoup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByUsedTrueAndRefrigeratorIdOrderByCreatedAtDesc(Long fridgeId);

    List<Ingredient> findByUsedFalseAndRefrigeratorId(Long fridgeId);

    Ingredient findFirstByTypeAndUsedFalseAndRefrigeratorOrderByCreatedAtAsc(int type, Refrigerator refrigerator);

    List<Ingredient> findByRiceCakeSoup(RiceCakeSoup riceCakeSoup);
}
