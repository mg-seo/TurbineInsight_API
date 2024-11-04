package com.yinseo.turbineInsightWeb.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.yinseo.turbineInsightWeb.entity.Business;
import com.yinseo.turbineInsightWeb.entity.Image;
import com.yinseo.turbineInsightWeb.repository.BusinessRepository;
import com.yinseo.turbineInsightWeb.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class ImageServiceImpl implements ImageService {

    private final AmazonS3 amazonS3;
    private final String bucketName = "turbine-image-storage-bucket";
    private final ImageRepository imageRepository;
    private final BusinessRepository businessRepository;

    @Autowired
    public ImageServiceImpl(AmazonS3 amazonS3, ImageRepository imageRepository, BusinessRepository businessRepository) {
        this.amazonS3 = amazonS3;
        this.imageRepository = imageRepository;
        this.businessRepository = businessRepository;
    }

    @Override
    public Image addImage(MultipartFile file, Long businessId) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found with id " + businessId));

        // S3에 파일 업로드
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String filePath = uploadFileToS3(file, fileName);

        // DB에 이미지 정보 저장
        Image image = new Image();
        image.setFileName(fileName);
        image.setFilePath(filePath);
        image.setUploadDate(LocalDateTime.now());
        image.setBusiness(business);

        return imageRepository.save(image);
    }

    private String uploadFileToS3(MultipartFile file, String fileName) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata));
        } catch (IOException e) {
            throw new RuntimeException("S3에 파일 업로드 실패", e);
        }
        // S3의 파일 URL을 반환
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    @Override
    public void deleteImage(Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found with id " + imageId));

        // S3에서 파일 삭제 (UUID가 붙은 파일명 사용)
        amazonS3.deleteObject(bucketName, image.getFileName());

        // DB에서 이미지 정보 삭제
        imageRepository.deleteById(imageId);
    }

    @Override
    public List<Image> getImagesByBusinessId(Long businessId) {
        return imageRepository.findByBusiness_BusinessId(businessId);
    }

    @Override
    public void deleteAllImagesForBusiness(Long businessId) {
        List<Image> images = imageRepository.findByBusiness_BusinessId(businessId);
        for (Image image : images) {
            amazonS3.deleteObject(bucketName, image.getFileName());
        }
    }
}

