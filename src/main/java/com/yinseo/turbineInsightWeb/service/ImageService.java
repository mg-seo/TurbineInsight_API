package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image addImage(MultipartFile file, Long businessId);        // 이미지 파일을 S3에 업로드 후 저장
    void deleteImage(Long imageId);                             // 이미지 삭제
    List<Image> getImagesByBusinessId(Long businessId);         // businessId로 이미지 목록 조회
    void deleteAllImagesForBusiness(Long businessId);
}

