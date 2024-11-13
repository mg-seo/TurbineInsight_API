//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinseo.turbineInsightWeb.entity;

import java.time.LocalDateTime;

public class EntityConverter {
    public EntityConverter() {
    }

    public static BusinessApp convertToAppBusiness(Business webBusiness) {
        return BusinessApp.builder().bno(webBusiness.getBusinessId()).title(webBusiness.getBusinessName()).regdate(webBusiness.getCreatedDate().toString()).update(webBusiness.getLastModifiedDate().toString()).userName(webBusiness.getUser() != null ? webBusiness.getUser().getUserName() : null).build();
    }

    public static Business convertToWebBusiness(BusinessApp appBusiness) {
        Business webBusiness = new Business();
        webBusiness.setBusinessId(appBusiness.getBno());
        webBusiness.setBusinessName(appBusiness.getTitle());
        if (appBusiness.getRegdate() != null && !appBusiness.getRegdate().isEmpty()) {
            webBusiness.setCreatedDate(LocalDateTime.parse(appBusiness.getRegdate()));
        } else {
            webBusiness.setCreatedDate(LocalDateTime.now());
        }

        if (appBusiness.getUpdate() != null && !appBusiness.getUpdate().isEmpty()) {
            webBusiness.setLastModifiedDate(LocalDateTime.parse(appBusiness.getUpdate()));
        } else {
            webBusiness.setLastModifiedDate(LocalDateTime.now());
        }

        User user = new User();
        user.setUserId("1234567890");
        webBusiness.setUser(user);
        return webBusiness;
    }

    public static MarkerApp convertToAppMarker(Marker webMarker) {
        if (webMarker == null) {
            throw new IllegalArgumentException("webMarker cannot be null");
        } else {
            Long bno = webMarker.getBusiness() != null ? webMarker.getBusiness().getBusinessId() : null;
            return MarkerApp.builder().mno(webMarker.getMarkerId() != null ? webMarker.getMarkerId() : 0L).title(webMarker.getMarkerName() != null ? webMarker.getMarkerName() : "").latitude(webMarker.getLatitude() != null ? webMarker.getLatitude() : 0.0).longitude(webMarker.getLongitude() != null ? webMarker.getLongitude() : 0.0).degree(webMarker.getAngle() != null ? webMarker.getAngle().longValue() : 0L).bno(bno).model(webMarker.getModelName() != null ? webMarker.getModelName() : "").build();
        }
    }

    public static Marker convertToWebMarker(MarkerApp appMarker) {
        Marker webMarker = new Marker();
        webMarker.setMarkerId(appMarker.getMno());
        webMarker.setMarkerName(appMarker.getTitle());
        webMarker.setLatitude(appMarker.getLatitude());
        webMarker.setLongitude(appMarker.getLongitude());
        webMarker.setAngle(appMarker.getDegree() != null ? appMarker.getDegree().intValue() : 0);
        webMarker.setModelName(appMarker.getModel());
        Business business = new Business();
        business.setBusinessId(appMarker.getBno());
        webMarker.setBusiness(business);
        return webMarker;
    }
}
