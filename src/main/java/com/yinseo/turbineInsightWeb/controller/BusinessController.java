package com.yinseo.turbineInsightWeb.controller;

import com.yinseo.turbineInsightWeb.entity.Business;
import com.yinseo.turbineInsightWeb.entity.Image;
import com.yinseo.turbineInsightWeb.entity.User;
import com.yinseo.turbineInsightWeb.service.BusinessService;
import com.yinseo.turbineInsightWeb.service.ImageService;
import com.yinseo.turbineInsightWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {

    private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);

    private final BusinessService businessService;
    private final UserService userService;
    private final ImageService imageService;

    @Autowired
    public BusinessController(BusinessService businessService, UserService userService, ImageService imageService) {
        this.businessService = businessService;
        this.userService = userService;
        this.imageService = imageService;
    }

    // 사용자 ID 존재 확인 (로그인 용도)
    @PostMapping("/checkUserId")
    public ResponseEntity<String> checkUserId(@RequestBody String userId) {
        Optional<User> user = userService.getUserById(userId);
        logger.info("Checking existence of user ID: {}", userId);

        if (user.isPresent()) {
            logger.info("User ID {} found", userId);
            return new ResponseEntity<>("OK", HttpStatus.OK);  // ID가 존재하면 OK 응답
        } else {
            logger.warn("User ID {} not found", userId);
            return new ResponseEntity<>("User ID not found", HttpStatus.NOT_FOUND);  // 존재하지 않으면 404
        }
    }

    // userId로 사업체 목록 조회
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<Business>> getBusinessesByUserId(@PathVariable String userId) {
        logger.info("Getting businesses for user ID: {}", userId);
        List<Business> businesses = businessService.getBusinessesByUserId(userId);
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }

    // 사업체 추가
    @PostMapping("/create")
    public ResponseEntity<Business> createBusiness(@RequestParam String businessName, @RequestParam String userId) {
        logger.info("Creating business with name: {} for user ID: {}", businessName, userId);
        Business newBusiness = businessService.createBusiness(businessName, userId);
        logger.info("Business created with ID: {}", newBusiness.getBusinessId());
        return new ResponseEntity<>(newBusiness, HttpStatus.CREATED);
    }

    // 사업체 이름 업데이트
    @PutMapping("/updateName/{businessId}")
    public ResponseEntity<Business> updateBusinessName(@PathVariable Long businessId, @RequestParam String businessName) {
        logger.info("Updating business name for business ID: {} to: {}", businessId, businessName);
        Business updatedBusiness = businessService.updateBusinessName(businessId, businessName);
        logger.info("Business ID: {} updated successfully", businessId);
        return new ResponseEntity<>(updatedBusiness, HttpStatus.OK);
    }

    // 사업체 메모 업데이트
    @PutMapping("/updateMemo/{businessId}")
    public ResponseEntity<Business> updateMemo(@PathVariable Long businessId, @RequestParam String memo) {
        logger.info("Updating memo for business ID: {}", businessId);
        Business updatedBusiness = businessService.updateMemo(businessId, memo);
        logger.info("Memo for business ID: {} updated successfully", businessId);
        return new ResponseEntity<>(updatedBusiness, HttpStatus.OK);
    }

    // 특정 사업체에 이미지 추가
    @PostMapping("/add")
    public ResponseEntity<Image> addImage(@RequestParam("file") MultipartFile file,
                                          @RequestParam("businessId") Long businessId) {
        Image addedImage = imageService.addImage(file, businessId);
        return new ResponseEntity<>(addedImage, HttpStatus.CREATED);
    }

    // 특정 이미지 삭제
    @DeleteMapping("/delete/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImage(imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 특정 사업체의 모든 이미지 조회
    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<Image>> getImagesByBusinessId(@PathVariable Long businessId) {
        List<Image> images = imageService.getImagesByBusinessId(businessId);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    // 사업체 삭제
    @DeleteMapping("/delete/{businessId}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Long businessId) {
        logger.info("Deleting business with ID: {}", businessId);
        businessService.deleteBusiness(businessId);
        logger.info("Business with ID: {} deleted successfully", businessId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
