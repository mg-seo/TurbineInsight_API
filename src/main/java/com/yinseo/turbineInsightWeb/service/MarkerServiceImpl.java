package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.Marker;
import com.yinseo.turbineInsightWeb.repository.MarkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("markerService")
public class MarkerServiceImpl implements MarkerService {

    @Autowired
    private MarkerRepository markerRepository;

    // 특정 사업 ID로 마커 목록 조회
    @Override
    public List<Marker> getMarkersById(Long businessId) {
        return markerRepository.findByBusiness_BusinessId(businessId);
    }

    // 마커 저장 (추가 또는 업데이트된 마커 저장)
    @Override
    public Marker saveMarker(Marker marker) {
        return markerRepository.save(marker);
    }

    // 마커 ID로 마커 조회
    @Override
    public Optional<Marker> getMarkerById(Long markerId) {
        return markerRepository.findById(markerId);
    }

    // 마커 ID로 마커 삭제
    @Override
    public void deleteMarkerById(Long markerId) {
        markerRepository.deleteById(markerId);
    }
}
