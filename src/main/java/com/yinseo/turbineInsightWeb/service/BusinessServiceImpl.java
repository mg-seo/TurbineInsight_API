package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.Business;

import java.util.List;

import com.yinseo.turbineInsightWeb.entity.User;
import com.yinseo.turbineInsightWeb.repository.BusinessRepository;
import com.yinseo.turbineInsightWeb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    @Autowired
    public BusinessServiceImpl(BusinessRepository businessRepository, UserRepository userRepository) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Business createBusiness(String businessName, String userId) {
        // userId로 User 객체를 조회하여 Business 객체에 설정
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        Business business = new Business();
        business.setBusinessName(businessName);
        business.setUser(user);
        business.setCreatedDate(LocalDateTime.now());
        business.setLastModifiedDate(LocalDateTime.now());

        return businessRepository.save(business);
    }

    @Override
    public List<Business> getBusinessesByUserId(String userId) {
        return businessRepository.findByUser_UserId(userId); // userId로 사업체 목록 조회
    }

    @Override
    public Business updateBusinessName(Long businessId, String businessName) {
        return businessRepository.findById(businessId)
                .map(existingBusiness -> {
                    existingBusiness.setBusinessName(businessName); // 사업체 이름만 업데이트
                    existingBusiness.setLastModifiedDate(LocalDateTime.now());
                    return businessRepository.save(existingBusiness);
                })
                .orElseThrow(() -> new RuntimeException("Business not found with id " + businessId));
    }

    @Override
    public Business updateMemo(Long businessId, String memo) {
        return businessRepository.findById(businessId)
                .map(existingBusiness -> {
                    existingBusiness.setMemo(memo);                 // memo 컬럼만 업데이트
                    existingBusiness.setLastModifiedDate(LocalDateTime.now());
                    return businessRepository.save(existingBusiness);
                })
                .orElseThrow(() -> new RuntimeException("Business not found with id " + businessId));
    }


    @Override
    public void deleteBusiness(Long businessId) {
        businessRepository.deleteById(businessId); // 특정 ID의 사업체 삭제
    }
}
