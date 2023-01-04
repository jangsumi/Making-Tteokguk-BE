package com.ricecakesoup.domain.Ingredient;

import com.ricecakesoup.domain.refrigerator.Refrigerator;
import com.ricecakesoup.domain.ricecakesoup.RiceCakeSoup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByUsedTrueAndRefrigeratorIdOrderByCreatedAtDesc(Long fridgeId);

    List<Ingredient> findByUsedFalseAndRefrigeratorId(Long fridgeId);

    Ingredient findFirstByTypeAndUsedFalseAndRefrigeratorOrderByCreatedAtAsc(int type, Refrigerator refrigerator);

    List<Ingredient> findByRiceCakeSoup(RiceCakeSoup riceCakeSoup);

//    int countBy
//    @Modifying
//    @Query("select " + "i.type, count(i) " + "from Ingredient i" + "Group BY u.type")

}

//@Query("select new com.example.Count(country, state, count(p) )
//        from Population p
//        group by p.country, p.state")
//        public List<Count> getCountByCountryAndState();
