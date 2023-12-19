package com.tejbhan.polygon;

import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Polygon;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    @Column(columnDefinition = "Geometry")
    private Polygon serviceArea;

}
