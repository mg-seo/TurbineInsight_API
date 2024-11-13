package com.yinseo.turbineInsightWeb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "BUSINESS")
@EntityListeners(AuditingEntityListener.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno; // PK

    @CreatedDate
    @Column(updatable = false)
    private String regdate;

    @CreatedDate
    @Column(updatable = true)
    private String update;

    @Column(length = 2000)
    private String title;

    private String userName;

}
