package com.example.controller;


import com.example.pojo.Result;
import com.example.utils.AliyunOSSOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 上传图片
 */
@RestController
public class UploadController {

    private static final Logger log = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    private AliyunOSSOperator aliyunOSS = new AliyunOSSOperator();

    //    //上传到本地
//    @PostMapping("/upload")
//    public Result uplode(String name,Integer age,MultipartFile file) throws Exception {
//        if (!file.isEmpty()) {
//            // 生成唯一文件名
//            String originalFilename = file.getOriginalFilename();
//            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
//            String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + extName;
//            // 拼接完整的文件路径
//            File targetFile = new File("D:/images/" + uniqueFileName);
//
//            // 如果目标目录不存在，则创建它
//            if (!targetFile.getParentFile().exists()) {
//                targetFile.getParentFile().mkdirs();
//            }
//            // 保存文件
//            file.transferTo(targetFile);
//        }
//        return Result.success();
//    }
    //上传到阿里云OSS
    @PostMapping("/upload")
    public Result uplode(MultipartFile file) throws Exception {
        log.info("开始上传文件: " + file.getOriginalFilename());
        byte[] content = file.getBytes();
        String originalFilename = file.getOriginalFilename();
        String url = aliyunOSS.upload(content, originalFilename);
        log.info("文件上传成功，URL: " + url);

        return Result.success(url);

    }
}

