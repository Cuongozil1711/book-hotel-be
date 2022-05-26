package main.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AmazonS3Service {
    private static final Logger logger = LogManager.getLogger(AmazonS3Service.class);
    private AmazonS3 s3client;
    @Value("${amazon.aws.url-endpoint}")
    private String endpointUrl;
    @Value("${amazon.s3.default-bucket}")
    private String bucketName;

    @Autowired
    public AmazonS3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        try {
            s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public List<String> uploadArrayFile(MultipartFile[] multipartFile) {
        String fileName = "";
        List<String> urlArrayFile = new ArrayList<>();
        try {
            for (int i = 0; i < multipartFile.length; i++) {
                File file = convertMultiPartToFile(multipartFile[i]);
                fileName = generateFileName(multipartFile[i]);
                this.uploadFileTos3bucket(fileName, file);
                file.delete();
                String fileUrl = endpointUrl + "/" + fileName;
                urlArrayFile.add(fileUrl);

            }
            return urlArrayFile;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }



    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        try {
            return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }



    public String uploadFileUrl(MultipartFile multipartFile) {
        String fileName = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            fileName = generateFileName(multipartFile);
            this.uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        String fileUrl = endpointUrl + "/" + fileName;
        return fileUrl;
    }




}
