package com.ringcentral.carsapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Brand {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
