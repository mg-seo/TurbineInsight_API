package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.RegulatedArea;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RegulatedAreaService {

    // 규제지역 추가
    RegulatedArea addRegulatedArea(MultipartFile file, String areaName, String userId);

    // 특정 사용자 ID로 규제지역 목록 조회
    List<RegulatedArea> getRegulatedAreasByUserId(String userId);

    // 규제지역 삭제
    void deleteRegulatedArea(Long areaId);

    // 규제지역 이름 업데이트
    RegulatedArea updateRegulatedArea(Long areaId, String areaName);
}

