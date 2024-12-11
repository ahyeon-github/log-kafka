package com.example.logreceive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileWriter;

@Service
public class KafkaConsumer {
    @Autowired
    private S3Uploader s3Uploader;

    @KafkaListener(topics = "log-topic", groupId = "itstudy")
    public void listen(String message) throws Exception {
        System.out.println("Received message: " + message);

        // 메시지를 log.txt 파일에 저장
        String filename = "log.txt";
        File file = new File(filename);
        FileWriter writer = new FileWriter(file, true);
        writer.write(message + "\n");
        writer.close();

        // S3에 업로드
        String uploadedFileName = CommonUtils.fileNameCreate(file.getName());
        String fileUrl = s3Uploader.upload(file, uploadedFileName);
        System.out.println("File uploaded to S3: " + fileUrl);
    }
}