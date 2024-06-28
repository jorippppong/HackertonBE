package com.hufshackerton.global.file;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Uploader {
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxSizeString;
    private final String PROFILE_IMG_DIR = "profile/";
    private final String MISSION_IMAGE_DIR = "mission/";

    // 프로필 사진 저장
    public String saveProfileImage(MultipartFile file){
        if(file == null || file.isEmpty()){
            return "";
        }
        return saveFile(file, PROFILE_IMG_DIR);
    }

    public String saveMissionImage(MultipartFile file) {
        if(file == null || file.isEmpty()){
            return "";
        }
        return saveFile(file, MISSION_IMAGE_DIR);
    }

    // 단일 파일 저장
    public String saveFile(MultipartFile file, String dir) {
        String randomFilename = generateRandomFilename(file);
        String filePath = dir+randomFilename;

        log.info("File upload started: " + filePath);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        // 파일 확장자를 기반으로 ContentType 설정
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String contentType = getContentType(fileExtension);
        metadata.setContentType(contentType);

        try {
            amazonS3.putObject(bucket, filePath, file.getInputStream(), metadata);
        } catch (AmazonS3Exception e) {
            log.error("Amazon S3 error while uploading file: " + e.getMessage());
            throw new RuntimeException("fail upload");
        } catch (SdkClientException e) {
            log.error("AWS SDK client error while uploading file: " + e.getMessage());
            throw new RuntimeException("fail upload");
        } catch (IOException e) {
            log.error("IO error while uploading file: " + e.getMessage());
            throw new RuntimeException("fail upload");
        }

        log.info("File upload completed: " + filePath);

        return amazonS3.getUrl(bucket, filePath).toString();
    }

    // 랜덤파일명 생성 (파일명 중복 방지)
    private String generateRandomFilename(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileExtension = validateFileExtension(originalFilename);
        String randomFilename = UUID.randomUUID() + "." + fileExtension;
        return randomFilename;
    }

    // 파일 확장자 체크
    private String validateFileExtension(String originalFilename) {
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        List<String> allowedExtensions = Arrays.asList("jpg", "png", "gif", "jpeg");

        if (!allowedExtensions.contains(fileExtension)) {
            throw new RuntimeException("fail upload 4");
        }
        return fileExtension;
    }

    // 파일 확장자를 추출하는 메소드
    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    // 파일 확장자에 따른 ContentType을 반환하는 메소드
    private String getContentType(String fileExtension) {
        Map<String, String> contentTypeMap = new HashMap<>();
        contentTypeMap.put("jpg", "image/jpeg");
        contentTypeMap.put("jpeg", "image/jpeg");
        contentTypeMap.put("png", "image/png");
        contentTypeMap.put("gif", "image/gif");

        return contentTypeMap.getOrDefault(fileExtension, "application/octet-stream");
    }



}
