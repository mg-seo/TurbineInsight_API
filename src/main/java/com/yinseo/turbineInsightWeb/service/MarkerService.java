//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.Marker;
import com.yinseo.turbineInsightWeb.entity.MarkerApp;
import java.util.List;
import java.util.Optional;

public interface MarkerService {
    List<Marker> getMarkersById(Long businessId);

    Marker saveMarker(Marker marker);

    Optional<Marker> getMarkerById(Long markerId);

    void deleteMarkerById(Long markerId);

    List<MarkerApp> getMarkersByBno(Long bno);

    Optional<MarkerApp> getMarkerByMno(Long mno);

    MarkerApp saveMarkerApp(MarkerApp markerApp);

    void deleteMarkerAppById(Long mno);
}
