package com.alkemy.ong.controller;

import com.alkemy.ong.service.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage/")
public class AmazonController {

    private AmazonService amazonService;

    @Autowired
    AmazonController(AmazonService amazonService) {
        this.amazonService = amazonService;
    }

    // Method recieves MultipartFile as a RequestPart
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonService.uploadFile(file);
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonService.deleteFileFromS3Bucket(fileUrl);
    }

    // Method recieves a Base64 File as a RequestPart
    @PostMapping("/uploadFile64")
    public String uploadFile64(@RequestPart(value = "file") String file, @RequestPart(value = "name") String fileName) {
        return this.amazonService.uploadFileBase64(file, fileName);
    }

}