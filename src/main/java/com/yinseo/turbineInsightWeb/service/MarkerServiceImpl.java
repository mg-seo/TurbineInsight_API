package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.Marker;
import com.yinseo.turbineInsightWeb.repository.MarkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("markerService")
public class MarkerServiceImpl implements MarkerService {

    @Autowired
    private MarkerRepository markerRepository;

    @Override
    public List<Marker> getMarkersById(Long business_id) {
        return markerRepository.findByBusiness_BusinessId(business_id);
    }

    @Override
    public Marker saveMarker(Marker marker) {
        return markerRepository.save(marker);
    }

    @Override
    public void deleteMarkerById(Long marker_id) {
        markerRepository.deleteById(marker_id);
    }
}
