package com.yinseo.turbineInsightWeb.repository;

import com.yinseo.turbineInsightWeb.entity.RegulatedArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegulatedAreaRepository extends JpaRepository<RegulatedArea, Long> {
    // 특정 사용자 ID에 해당하는 규제지역 목록을 조회하는 메서드
    List<RegulatedArea> findByUser_UserId(String userId);
}
