package com.yinseo.turbineInsightWeb.controller;

import com.yinseo.turbineInsightWeb.entity.Business;
import com.yinseo.turbineInsightWeb.entity.User;
import com.yinseo.turbineInsightWeb.service.BusinessService;
import com.yinseo.turbineInsightWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/businesses")
@CrossOrigin(origins = "http://localhost:3000")
public class BusinessController {

    private final BusinessService businessService;
    private final UserService userService;

    @Autowired
    public BusinessController(BusinessService businessService, UserService userService) {
        this.businessService = businessService;
        this.userService = userService;
    }

    // 사용자 ID 존재 확인 (로그인 용도)
    @PostMapping("/checkUserId")
    public ResponseEntity<String> checkUserId(@RequestBody String userId) {
        Optional<User> user = userService.getUserById(userId);

        if (user.isPresent()) {
            return new ResponseEntity<>("OK", HttpStatus.OK);  // ID가 존재하면 OK 응답
        } else {
            return new ResponseEntity<>("User ID not found", HttpStatus.NOT_FOUND);  // 존재하지 않으면 404
        }
    }

    // userId로 사업체 목록 조회
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<Business>> getBusinessesByUserId(@PathVariable String userId) {
        List<Business> businesses = businessService.getBusinessesByUserId(userId);
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }

    // 사업체 추가
    @PostMapping("/create")
    public ResponseEntity<Business> createBusiness(@RequestParam String businessName, @RequestParam String userId) {
        Business newBusiness = businessService.createBusiness(businessName, userId);
        return new ResponseEntity<>(newBusiness, HttpStatus.CREATED);
    }

    // 사업체 이름 업데이트
    @PutMapping("/updateName/{businessId}")
    public ResponseEntity<Business> updateBusinessName(@PathVariable Long businessId, @RequestParam String businessName) {
        Business updatedBusiness = businessService.updateBusinessName(businessId, businessName);
        return new ResponseEntity<>(updatedBusiness, HttpStatus.OK);
    }

    // 사업체 메모 업데이트
    @PutMapping("/updateMemo/{businessId}")
    public ResponseEntity<Business> updateMemo(@PathVariable Long businessId, @RequestParam String memo) {
        Business updatedBusiness = businessService.updateMemo(businessId, memo);
        return new ResponseEntity<>(updatedBusiness, HttpStatus.OK);
    }

    // 사업체 삭제
    @DeleteMapping("/delete/{businessId}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Long businessId) {
        businessService.deleteBusiness(businessId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
