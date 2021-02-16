package com.ringcentral.carsapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @OneToMany(mappedBy = "country")
    private List<Brand> brands;
}
