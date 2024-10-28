package com.yinseo.turbineInsightWeb.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "markers")
@Getter
@Setter
@NoArgsConstructor
public class Marker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marker_id")
    private Long markerId;

    @Column(name = "marker_name", length = 60)
    private String markerName;

    @Column(name = "latitude", precision = 10, scale = 7)
    private Double latitude;

    @Column(name = "longitude", precision = 10, scale = 7)
    private Double longitude;

    @Column(name = "angle")
    private Integer angle;

    @Column(name = "model_name", length = 20)
    private String modelName;

    @ManyToOne
    @JoinColumn(name = "business_id", referencedColumnName = "business_id")
    private Business business;
}

