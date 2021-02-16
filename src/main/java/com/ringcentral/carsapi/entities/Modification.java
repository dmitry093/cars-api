package com.ringcentral.carsapi.entities;

import com.ringcentral.carsapi.enums.EngineType;
import com.ringcentral.carsapi.enums.FuelType;
import com.ringcentral.carsapi.enums.GearboxType;
import com.ringcentral.carsapi.enums.WheelDriveType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Modification {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "generation_id")
    private Generation generation;

    @Column(name = "fuel_type")
    @ColumnTransformer(read = "UPPER(fuel_type)")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(name = "engine_type")
    @ColumnTransformer(read = "UPPER(engine_type)")
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column(name = "engine_displacement")
    private Integer engineDisplacement;

    @Column(name = "gearbox_type")
    @ColumnTransformer(read = "UPPER(gearbox_type)")
    @Enumerated(EnumType.STRING)
    private GearboxType gearboxType;

    @Column(name = "wheeldrive_type")
    @ColumnTransformer(read = "UPPER(wheeldrive_type)")
    @Enumerated(EnumType.STRING)
    private WheelDriveType wheelDriveType;

    @Column
    private String bodyStyle;

    @Column
    private Double acceleration;

    @Column
    private Integer hp;

    @Column(name = "max_speed")
    private Integer maxSpeed;
}
