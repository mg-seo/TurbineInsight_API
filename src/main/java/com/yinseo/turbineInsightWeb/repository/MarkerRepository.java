package com.yinseo.turbineInsightWeb.repository;

import com.yinseo.turbineInsightWeb.entity.Marker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkerRepository extends JpaRepository<Marker, Long> {

    List<Marker> findByBusiness_BusinessId(Long businessId);

}

