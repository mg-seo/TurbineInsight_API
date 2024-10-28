package com.yinseo.turbineInsightWeb.repository;

import com.yinseo.turbineInsightWeb.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    List<Business> findByUser_UserId(String userId);
}

