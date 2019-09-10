package com.lyj.shiroweb.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lyj
 * @date 2019/9/10 11:16
 */
@RestController
public class UploadFileController {
    @PostMapping(value = "/uploadFiles")
    public void uploadFiles(@RequestBody List<MultipartFile> fileList){
        fileList.forEach(file -> {
            System.out.println(file.getOriginalFilename());
        });
    }
}
