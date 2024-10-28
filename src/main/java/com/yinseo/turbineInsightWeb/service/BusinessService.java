package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.Business;

import java.util.List;

public interface BusinessService {
    List<Business> getBusinessById(String userId);
}
