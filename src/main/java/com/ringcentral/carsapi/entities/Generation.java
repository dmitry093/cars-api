package com.ringcentral.carsapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Generation {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @Column
    private String years;

    @Column
    private Integer length;

    @Column
    private Integer width;

    @Column
    private Integer height;
}
