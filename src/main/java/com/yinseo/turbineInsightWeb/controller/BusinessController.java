//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinseo.turbineInsightWeb.controller;

import com.yinseo.turbineInsightWeb.entity.Business;
import com.yinseo.turbineInsightWeb.entity.BusinessApp;
import com.yinseo.turbineInsightWeb.entity.Image;
import com.yinseo.turbineInsightWeb.entity.RegulatedArea;
import com.yinseo.turbineInsightWeb.entity.User;
import com.yinseo.turbineInsightWeb.service.BusinessService;
import com.yinseo.turbineInsightWeb.service.ImageService;
import com.yinseo.turbineInsightWeb.service.RegulatedAreaService;
import com.yinseo.turbineInsightWeb.service.UserService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/api/businesses"})
public class BusinessController {
    private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);
    private final BusinessService businessService;
    private final UserService userService;
    private final ImageService imageService;
    private final RegulatedAreaService regulatedAreaService;

    @Autowired
    public BusinessController(BusinessService businessService, UserService userService, ImageService imageService, RegulatedAreaService regulatedAreaService) {
        this.businessService = businessService;
        this.userService = userService;
        this.imageService = imageService;
        this.regulatedAreaService = regulatedAreaService;
    }

    @PostMapping({"/checkUserId"})
    public ResponseEntity<String> checkUserId(@RequestBody String userId) {
        Optional<User> user = this.userService.getUserById(userId);
        logger.info("Checking existence of user ID: {}", userId);
        if (user.isPresent()) {
            logger.info("User ID {} found", userId);
            return new ResponseEntity("OK", HttpStatus.OK);
        } else {
            logger.warn("User ID {} not found", userId);
            return new ResponseEntity("User ID not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping({"/{userId}"})
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        Optional<User> user = this.userService.getUserById(userId);
        return user.isPresent() ? ResponseEntity.ok((User)user.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping({"/list/{userId}"})
    public ResponseEntity<List<Business>> getBusinessesByUserId(@PathVariable String userId) {
        logger.info("Getting businesses for user ID: {}", userId);
        List<Business> businesses = this.businessService.getBusinessesByUserId(userId);
        return new ResponseEntity(businesses, HttpStatus.OK);
    }

    @PostMapping({"/create"})
    public ResponseEntity<Business> createBusiness(@RequestParam String businessName, @RequestParam String userId) {
        logger.info("Creating business with name: {} for user ID: {}", businessName, userId);
        Business newBusiness = this.businessService.createBusiness(businessName, userId);
        logger.info("Business created with ID: {}", newBusiness.getBusinessId());
        return new ResponseEntity(newBusiness, HttpStatus.CREATED);
    }

    @PutMapping({"/updateName/{businessId}"})
    public ResponseEntity<Business> updateBusinessName(@PathVariable Long businessId, @RequestParam String businessName) {
        logger.info("Updating business name for business ID: {} to: {}", businessId, businessName);
        Business updatedBusiness = this.businessService.updateBusinessName(businessId, businessName);
        logger.info("Business ID: {} updated successfully", businessId);
        return new ResponseEntity(updatedBusiness, HttpStatus.OK);
    }

    @PutMapping({"/updateMemo/{businessId}"})
    public ResponseEntity<Business> updateMemo(@PathVariable Long businessId, @RequestParam String memo) {
        logger.info("Updating memo for business ID: {}", businessId);
        Business updatedBusiness = this.businessService.updateMemo(businessId, memo);
        logger.info("Memo for business ID: {} updated successfully", businessId);
        return new ResponseEntity(updatedBusiness, HttpStatus.OK);
    }

    @PostMapping({"/add"})
    public ResponseEntity<Image> addImage(@RequestParam("file") MultipartFile file, @RequestParam("businessId") Long businessId) {
        Image addedImage = this.imageService.addImage(file, businessId);
        return new ResponseEntity(addedImage, HttpStatus.CREATED);
    }

    @DeleteMapping({"/deleteImage/{imageId}"})
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        this.imageService.deleteImage(imageId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping({"/business/{businessId}"})
    public ResponseEntity<List<Image>> getImagesByBusinessId(@PathVariable Long businessId) {
        List<Image> images = this.imageService.getImagesByBusinessId(businessId);
        return new ResponseEntity(images, HttpStatus.OK);
    }

    @DeleteMapping({"/delete/{businessId}"})
    public ResponseEntity<Void> deleteBusiness(@PathVariable Long businessId) {
        logger.info("Deleting business with ID: {}", businessId);
        this.imageService.deleteAllImagesForBusiness(businessId);
        this.businessService.deleteBusiness(businessId);
        logger.info("Business with ID: {} and associated images deleted successfully", businessId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping({"/regulatedArea/add"})
    public ResponseEntity<RegulatedArea> addRegulatedArea(@RequestParam("file") MultipartFile file, @RequestParam String areaName, @RequestParam String userId) {
        logger.info("Adding regulated area with name: {} for user ID: {}", areaName, userId);
        RegulatedArea newArea = this.regulatedAreaService.addRegulatedArea(file, areaName, userId);
        return new ResponseEntity(newArea, HttpStatus.CREATED);
    }

    @GetMapping({"/regulatedArea/list/{userId}"})
    public ResponseEntity<List<RegulatedArea>> getRegulatedAreasByUserId(@PathVariable String userId) {
        logger.info("Getting regulated areas for user ID: {}", userId);
        List<RegulatedArea> areas = this.regulatedAreaService.getRegulatedAreasByUserId(userId);
        return new ResponseEntity(areas, HttpStatus.OK);
    }

    @PutMapping({"/regulatedArea/update/{areaId}"})
    public ResponseEntity<RegulatedArea> updateRegulatedArea(@PathVariable Long areaId, @RequestParam String areaName) {
        logger.info("Updating regulated area ID: {} with new name: {}", areaId, areaName);
        RegulatedArea updatedArea = this.regulatedAreaService.updateRegulatedArea(areaId, areaName);
        return new ResponseEntity(updatedArea, HttpStatus.OK);
    }

    @DeleteMapping({"/regulatedArea/delete/{areaId}"})
    public ResponseEntity<Void> deleteRegulatedArea(@PathVariable Long areaId) {
        logger.info("Deleting regulated area with ID: {}", areaId);
        this.regulatedAreaService.deleteRegulatedArea(areaId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping({"/get/business/all"})
    public List<BusinessApp> getTurbineList() {
        return this.businessService.getBusinessList();
    }

    @PostMapping({"/post/business/add"})
    public ResponseEntity<BusinessApp> addBusiness(@RequestBody BusinessApp businessApp) {
        try {
            BusinessApp savedBusinessApp = this.businessService.saveBusinessApp(businessApp);
            return new ResponseEntity(savedBusinessApp, HttpStatus.CREATED);
        } catch (Exception var3) {
            Exception e = var3;
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"/post/business/update/{bno}"})
    public ResponseEntity<BusinessApp> updateBusiness(@PathVariable Long bno, @RequestBody BusinessApp businessApp) {
        try {
            Optional<BusinessApp> existingBusiness = this.businessService.getBusinessByBno(bno);
            if (existingBusiness.isPresent()) {
                businessApp.setBno(bno);
                BusinessApp updatedBusiness = this.businessService.saveBusinessApp(businessApp);
                return new ResponseEntity(updatedBusiness, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception var5) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"/delete/business"})
    public ResponseEntity<Void> deleteBusiness(@RequestBody List<Long> ids) {
        try {
            this.businessService.deleteBusinessApp(ids);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception var3) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
