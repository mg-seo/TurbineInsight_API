package com.yinseo.turbineInsightWeb.controller;

import com.yinseo.turbineInsightWeb.entity.Marker;
import com.yinseo.turbineInsightWeb.service.MarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/businesses/map")
public class MapController {

    private final MarkerService markerService;

    @Autowired
    public MapController(MarkerService markerService) {
        this.markerService = markerService;
    }

    // 특정 사업 ID로 마커 조회
    @GetMapping("/get/marker/{businessId}")
    public ResponseEntity<List<Marker>> getMarkersByBusinessId(@PathVariable Long businessId) {
        List<Marker> markers = markerService.getMarkersById(businessId);
        System.out.println(markers.toString()); // 로그로 마커 리스트 확인
        return new ResponseEntity<>(markers, HttpStatus.OK); // 빈 리스트 포함하여 반환
    }

    // 새로운 마커 추가
    @PostMapping("/post/marker/add")
    public ResponseEntity<Marker> addMarker(@RequestBody Marker marker) {
        try {
            Marker savedMarker = markerService.saveMarker(marker);
            return new ResponseEntity<>(savedMarker, HttpStatus.CREATED); // 201 Created 반환
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 오류 발생 시 400 Bad Request 반환
        }
    }

    // 마커 업데이트
    @PostMapping("/post/marker/update/{markerId}")
    public ResponseEntity<Marker> updateMarker(@PathVariable Long markerId, @RequestBody Marker marker) {
        try {
            Optional<Marker> existingMarkerOpt = markerService.getMarkerById(markerId);
            if (existingMarkerOpt.isPresent()) {
                Marker existingMarker = existingMarkerOpt.get();

                // null 체크 후 필드 덮어쓰기
                if (marker.getLatitude() != null) {
                    existingMarker.setLatitude(marker.getLatitude());
                }
                if (marker.getLongitude() != null) {
                    existingMarker.setLongitude(marker.getLongitude());
                }
                if (marker.getAngle() != null) {
                    existingMarker.setAngle(marker.getAngle());
                }
                if (marker.getMarkerName() != null) {
                    existingMarker.setMarkerName(marker.getMarkerName());
                }
                if (marker.getModelName() != null) {
                    existingMarker.setModelName(marker.getModelName());
                }

                Marker updatedMarker = markerService.saveMarker(existingMarker);
                return new ResponseEntity<>(updatedMarker, HttpStatus.OK); // 200 OK 반환
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 마커가 없는 경우 404 Not Found
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 오류 발생 시 400 Bad Request 반환
        }
    }

    // 마커 삭제
    @DeleteMapping("/delete/marker/{markerId}")
    public ResponseEntity<Void> deleteMarker(@PathVariable Long markerId) {
        try {
            markerService.deleteMarkerById(markerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
