//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinseo.turbineInsightWeb.controller;

import com.yinseo.turbineInsightWeb.entity.Marker;
import com.yinseo.turbineInsightWeb.entity.MarkerApp;
import com.yinseo.turbineInsightWeb.service.MarkerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api/businesses/map"})
public class MapController {
    private final MarkerService markerService;

    @Autowired
    public MapController(MarkerService markerService) {
        this.markerService = markerService;
    }

    @GetMapping({"/get/marker/{businessId}"})
    public ResponseEntity<List<Marker>> getMarkersByBusinessId(@PathVariable Long businessId) {
        List<Marker> markers = this.markerService.getMarkersById(businessId);
        return new ResponseEntity(markers, HttpStatus.OK);
    }

    @GetMapping({"/get/app/marker/{bno}"})
    public ResponseEntity<List<MarkerApp>> getAppMarkersByBno(@PathVariable Long bno) {
        List<MarkerApp> appMarkers = this.markerService.getMarkersByBno(bno);
        return new ResponseEntity(appMarkers, HttpStatus.OK);
    }

    @PostMapping({"/post/marker/add"})
    public ResponseEntity<Marker> addMarker(@RequestBody Marker marker) {
        try {
            Marker savedMarker = this.markerService.saveMarker(marker);
            return new ResponseEntity(savedMarker, HttpStatus.CREATED);
        } catch (Exception var3) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"/post/app/marker/add"})
    public ResponseEntity<MarkerApp> addAppMarker(@RequestBody MarkerApp markerApp) {
        try {
            System.out.println("Received MarkerApp: " + markerApp);
            MarkerApp savedAppMarker = this.markerService.saveMarkerApp(markerApp);
            return new ResponseEntity(savedAppMarker, HttpStatus.CREATED);
        } catch (Exception var3) {
            Exception e = var3;
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"/post/marker/update/{markerId}"})
    public ResponseEntity<Marker> updateMarker(@PathVariable Long markerId, @RequestBody Marker marker) {
        try {
            Optional<Marker> existingMarkerOpt = this.markerService.getMarkerById(markerId);
            if (existingMarkerOpt.isPresent()) {
                Marker existingMarker = (Marker)existingMarkerOpt.get();
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

                Marker updatedMarker = this.markerService.saveMarker(existingMarker);
                return new ResponseEntity(updatedMarker, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception var6) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"/post/app/marker/update/{mno}"})
    public ResponseEntity<MarkerApp> updateAppMarker(@PathVariable Long mno, @RequestBody MarkerApp markerApp) {
        try {
            Optional<MarkerApp> existingAppMarkerOpt = this.markerService.getMarkerByMno(mno);
            if (existingAppMarkerOpt.isPresent()) {
                MarkerApp existingAppMarker = (MarkerApp)existingAppMarkerOpt.get();
                if (markerApp.getLatitude() != null) {
                    existingAppMarker.setLatitude(markerApp.getLatitude());
                }

                if (markerApp.getLongitude() != null) {
                    existingAppMarker.setLongitude(markerApp.getLongitude());
                }

                if (markerApp.getDegree() != null) {
                    existingAppMarker.setDegree(markerApp.getDegree());
                }

                if (markerApp.getTitle() != null) {
                    existingAppMarker.setTitle(markerApp.getTitle());
                }

                if (markerApp.getModel() != null) {
                    existingAppMarker.setModel(markerApp.getModel());
                }

                MarkerApp updatedAppMarker = this.markerService.saveMarkerApp(existingAppMarker);
                return new ResponseEntity(updatedAppMarker, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception var6) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping({"/delete/marker/{markerId}"})
    public ResponseEntity<Void> deleteMarker(@PathVariable Long markerId) {
        try {
            this.markerService.deleteMarkerById(markerId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception var3) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping({"/delete/app/marker/{mno}"})
    public ResponseEntity<Void> deleteAppMarker(@PathVariable Long mno) {
        try {
            this.markerService.deleteMarkerAppById(mno);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception var3) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
