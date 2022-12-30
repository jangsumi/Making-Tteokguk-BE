package com.ricecakesoup.domain.ricecakesoup;

import com.ricecakesoup.domain.common.BaseTimeEntity;
import com.ricecakesoup.domain.refrigerator.Refrigerator;
import lombok.Getter;

import javax.persistence.*;
@Entity
@Getter
public class RiceCakeSoup extends BaseTimeEntity {
    public RiceCakeSoup() {}

    private RiceCakeSoup(final Refrigerator refrigerator) {
        this.refrigerator = refrigerator;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rice_cake_soup_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "refrigerator_id")
    private Refrigerator refrigerator;


    public static RiceCakeSoup newInstance(final Refrigerator refrigerator) {
        return new RiceCakeSoup(refrigerator);
    }
}
