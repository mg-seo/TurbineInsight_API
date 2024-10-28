package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.Marker;

import java.util.List;

public interface MarkerService {

    //사업아이디로 마커 불러오기
    List<Marker> getMarkersById(Long business_id);

    //저장
    Marker saveMarker(Marker marker);

    void deleteMarkerById(Long marker_id);


}
