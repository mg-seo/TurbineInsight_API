package com.yinseo.turbineInsightWeb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "MARKER")
@EntityListeners(AuditingEntityListener.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkerApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno; // PK

    @CreatedDate
    @Column(updatable = false)
    private String regdate;

    @CreatedDate
    @Column(updatable = true)
    private String update;

    private String title;
    private Long degree;
    private Double latitude;
    private Double longitude;
    private Long bno;
    private String model;
}
