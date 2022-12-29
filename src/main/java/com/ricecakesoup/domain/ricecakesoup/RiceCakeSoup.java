package com.ricecakesoup.domain.ricecakesoup;

import com.ricecakesoup.domain.refrigerator.Refrigerator;

import javax.persistence.*;

public class RiceCakeSoup {
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


    private static RiceCakeSoup newInstance(final Refrigerator refrigerator) {
        return new RiceCakeSoup(refrigerator);
    }
}
