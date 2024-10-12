package com.dreamer.demoproject.controller;

import com.dreamer.demoproject.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String>upload(MultipartFile file) throws IOException {
        //将前端返回的文件写入本地磁盘
        String originalFileName= file.getOriginalFilename();
        //加上UUID字符保证文件名唯一，避免不同用户上传同名文件导致覆盖
        String filename= UUID.randomUUID().toString()+originalFileName.substring(originalFileName.lastIndexOf('.'));
        file.transferTo(new File("C:\\Users\\23979\\Desktop\\test\\"+originalFileName));
        return Result.success("url地址");
    }
}
