package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.Business;
import com.yinseo.turbineInsightWeb.entity.BusinessApp;
import com.yinseo.turbineInsightWeb.entity.EntityConverter;
import com.yinseo.turbineInsightWeb.entity.User;
import com.yinseo.turbineInsightWeb.repository.BusinessRepository;
import com.yinseo.turbineInsightWeb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private UserRepository userRepository;

    // 웹 엔티티 메서드
    @Override
    public Business createBusiness(String businessName, String userId) {
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
        return businessRepository.findByUser_UserId(userId);
    }

    @Override
    public Business updateBusinessName(Long businessId, String businessName) {
        return businessRepository.findById(businessId)
                .map(existingBusiness -> {
                    existingBusiness.setBusinessName(businessName);
                    existingBusiness.setLastModifiedDate(LocalDateTime.now());
                    return businessRepository.save(existingBusiness);
                })
                .orElseThrow(() -> new RuntimeException("Business not found with id " + businessId));
    }

    @Override
    public Business updateMemo(Long businessId, String memo) {
        return businessRepository.findById(businessId)
                .map(existingBusiness -> {
                    existingBusiness.setMemo(memo);
                    existingBusiness.setLastModifiedDate(LocalDateTime.now());
                    return businessRepository.save(existingBusiness);
                })
                .orElseThrow(() -> new RuntimeException("Business not found with id " + businessId));
    }

    @Override
    public void deleteBusiness(Long businessId) {
        businessRepository.deleteById(businessId);
    }

    // 앱 엔티티 메서드 (변환 작업 포함)
    @Override
    public List<BusinessApp> getBusinessList() {
        List<Business> webBusinesses = businessRepository.findAllByOrderByBusinessIdDesc();
        return webBusinesses.stream()
                .map(EntityConverter::convertToAppBusiness)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BusinessApp> getBusinessByBno(Long bno) {
        Optional<Business> webBusiness = businessRepository.findById(bno);
        return webBusiness.map(EntityConverter::convertToAppBusiness);
    }

    @Override
    public BusinessApp saveBusinessApp(BusinessApp businessApp) {
        Business webBusiness = EntityConverter.convertToWebBusiness(businessApp);
        Business savedBusiness = businessRepository.save(webBusiness);
        return EntityConverter.convertToAppBusiness(savedBusiness);
    }

    @Override
    public void deleteBusinessApp(List<Long> ids) {
        businessRepository.deleteAllById(ids);
    }
}