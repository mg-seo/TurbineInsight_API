//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.Business;
import com.yinseo.turbineInsightWeb.entity.BusinessApp;
import java.util.List;
import java.util.Optional;

public interface BusinessService {
    Business createBusiness(String businessName, String userId);

    List<Business> getBusinessesByUserId(String userId);

    Business updateBusinessName(Long businessId, String businessName);

    Business updateMemo(Long businessId, String memo);

    void deleteBusiness(Long businessId);

    List<BusinessApp> getBusinessList();

    Optional<BusinessApp> getBusinessByBno(Long bno);

    BusinessApp saveBusinessApp(BusinessApp businessApp);

    void deleteBusinessApp(List<Long> ids);
}
