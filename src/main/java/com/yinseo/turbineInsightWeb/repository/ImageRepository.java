package com.yinseo.turbineInsightWeb.repository;

import com.yinseo.turbineInsightWeb.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByBusiness_BusinessId(Long businessId);
}

