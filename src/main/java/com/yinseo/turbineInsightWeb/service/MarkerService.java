package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.Marker;

import java.util.List;
import java.util.Optional;

public interface MarkerService {

    // 특정 사업 ID로 마커 조회
    List<Marker> getMarkersById(Long businessId);

    // 마커 저장 (새 마커 추가 및 기존 마커 업데이트)
    Marker saveMarker(Marker marker);

    // 마커 검색 (ID로 검색)
    Optional<Marker> getMarkerById(Long markerId);

    // 마커 삭제
    void deleteMarkerById(Long markerId);
}
