package com.yinseo.turbineInsightWeb.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.yinseo.turbineInsightWeb.entity.Business;
import com.yinseo.turbineInsightWeb.entity.Image;
import com.yinseo.turbineInsightWeb.repository.BusinessRepository;
import com.yinseo.turbineInsightWeb.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final AmazonS3 amazonS3;
    private final String bucketName = "your-bucket-name";
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
        String fileName = file.getOriginalFilename();
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
        File tempFile = convertMultiPartToFile(file);
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, tempFile));
        tempFile.delete();

        // S3의 파일 URL을 반환
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    private File convertMultiPartToFile(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }

    @Override
    public void deleteImage(Long imageId) {
        imageRepository.deleteById(imageId);
    }

    @Override
    public List<Image> getImagesByBusinessId(Long businessId) {
        return imageRepository.findByBusiness_BusinessId(businessId);
    }
}

