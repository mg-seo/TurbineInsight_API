package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.EntityConverter;
import com.yinseo.turbineInsightWeb.entity.Marker;
import com.yinseo.turbineInsightWeb.entity.MarkerApp;
import com.yinseo.turbineInsightWeb.repository.MarkerRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("markerService")
public class MarkerServiceImpl implements MarkerService {

    @Autowired
    private MarkerRepository markerRepository;

    public MarkerServiceImpl() {}

    @Override
    public List<Marker> getMarkersById(Long businessId) {
        return markerRepository.findByBusiness_BusinessId(businessId);
    }

    @Override
    public Marker saveMarker(Marker marker) {
        return markerRepository.save(marker);
    }

    @Override
    public Optional<Marker> getMarkerById(Long markerId) {
        return markerRepository.findById(markerId);
    }

    @Override
    public void deleteMarkerById(Long markerId) {
        markerRepository.deleteById(markerId);
    }

    @Override
    public List<MarkerApp> getMarkersByBno(Long bno) {
        List<Marker> webMarkers = markerRepository.findByBusiness_BusinessId(bno);
        return webMarkers.stream()
                .map(EntityConverter::convertToAppMarker)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MarkerApp> getMarkerByMno(Long mno) {
        Optional<Marker> webMarker = markerRepository.findById(mno);
        return webMarker.map(EntityConverter::convertToAppMarker);
    }

    @Override
    public MarkerApp saveMarkerApp(MarkerApp markerApp) {
        Marker webMarker = EntityConverter.convertToWebMarker(markerApp);
        Marker savedMarker = markerRepository.save(webMarker);
        return EntityConverter.convertToAppMarker(savedMarker);
    }

    @Override
    public void deleteMarkerAppById(Long mno) {
        markerRepository.deleteById(mno);
    }
}
