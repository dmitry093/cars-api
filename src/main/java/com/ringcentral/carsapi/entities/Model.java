package com.ringcentral.carsapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Model {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "segment_id")
    private Segment segment;
}
