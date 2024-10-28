package com.yinseo.turbineInsightWeb.controller;

import com.yinseo.turbineInsightWeb.entity.Marker;
import com.yinseo.turbineInsightWeb.service.MarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/businesses/map")
public class MapController {

    private final MarkerService markerService;

    @Autowired
    public MapController(MarkerService markerService) {
        this.markerService = markerService;
    }

    @GetMapping("/{businessId}")
    public List<Marker> getMarkersByBusinessId(@PathVariable Long businessId) {
        return markerService.getMarkersById(businessId);
    }

    // 새로운 마커 저장
    @PostMapping
    public Marker saveMarker(@RequestBody Marker marker) {
        return markerService.saveMarker(marker);
    }

    // 마커 ID로 마커 삭제
    @DeleteMapping("/{markerId}")
    public void deleteMarkerById(@PathVariable Long markerId) {
        markerService.deleteMarkerById(markerId);
    }
}
