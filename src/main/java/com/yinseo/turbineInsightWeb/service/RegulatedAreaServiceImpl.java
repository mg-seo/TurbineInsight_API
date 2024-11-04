package com.yinseo.turbineInsightWeb.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.yinseo.turbineInsightWeb.entity.RegulatedArea;
import com.yinseo.turbineInsightWeb.entity.User;
import com.yinseo.turbineInsightWeb.repository.RegulatedAreaRepository;
import com.yinseo.turbineInsightWeb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RegulatedAreaServiceImpl implements RegulatedAreaService {

    private final AmazonS3 amazonS3;
    private final String bucketName = "turbine-regulated-area-storage-bucket";
    private final RegulatedAreaRepository regulatedAreaRepository;
    private final UserRepository userRepository;

    @Autowired
    public RegulatedAreaServiceImpl(AmazonS3 amazonS3, RegulatedAreaRepository regulatedAreaRepository, UserRepository userRepository) {
        this.amazonS3 = amazonS3;
        this.regulatedAreaRepository = regulatedAreaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RegulatedArea addRegulatedArea(MultipartFile file, String areaName, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        // S3에 파일 업로드
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String filePath = uploadFileToS3(file, fileName);

        // DB에 규제지역 정보 저장
        RegulatedArea regulatedArea = new RegulatedArea();
        regulatedArea.setAreaName(areaName);
        regulatedArea.setFileName(fileName);
        regulatedArea.setFilePath(filePath);
        regulatedArea.setUploadDate(LocalDateTime.now());
        regulatedArea.setUser(user);

        return regulatedAreaRepository.save(regulatedArea);
    }

    private String uploadFileToS3(MultipartFile file, String fileName) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata));
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
        // S3의 파일 URL을 반환
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    @Override
    public List<RegulatedArea> getRegulatedAreasByUserId(String userId) {
        return regulatedAreaRepository.findByUser_UserId(userId);
    }

    @Override
    public void deleteRegulatedArea(Long areaId) {
        RegulatedArea regulatedArea = regulatedAreaRepository.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Regulated Area not found with id " + areaId));

        // S3에서 파일 삭제
        amazonS3.deleteObject(bucketName, regulatedArea.getFileName());

        // DB에서 규제지역 정보 삭제
        regulatedAreaRepository.deleteById(areaId);
    }

    @Override
    public RegulatedArea updateRegulatedArea(Long areaId, String areaName) {
        RegulatedArea regulatedArea = regulatedAreaRepository.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Regulated Area not found with id " + areaId));

        regulatedArea.setAreaName(areaName);
        return regulatedAreaRepository.save(regulatedArea);
    }
}
