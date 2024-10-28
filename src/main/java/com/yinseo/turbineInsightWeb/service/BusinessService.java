package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.Business;

import java.util.List;

public interface BusinessService {
    Business createBusiness(String businessName, String userId); // 사업체 이름과 userId로 추가
    List<Business> getBusinessesByUserId(String userId); // userId로 사업체 목록 조회
    Business updateBusinessName(Long businessId, String businessName); // 사업체 이름만 업데이트
    Business updateMemo(Long businessId, String memo);           // memo 컬럼만 업데이트
    void deleteBusiness(Long businessId);                        // 특정 ID의 사업체 삭제
}
