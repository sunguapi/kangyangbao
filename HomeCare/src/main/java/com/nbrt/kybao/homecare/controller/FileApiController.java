package com.nbrt.kybao.homecare.controller;


import com.nbrt.kybao.homecare.service.FileService;
import com.nbrt.kybao.homecare.utils.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Api(tags = "阿里云OSS工具")
@RestController
public class FileApiController {

    private final FileService fileService;

    public FileApiController(FileService fileService) {
        this.fileService = fileService;
    }

    //上传文件到阿里云oss
    @ApiOperation("文件上传")
    @PostMapping("/api/oss/file/fileUpload")
    public R fileUpload(MultipartFile file) {
        //获取上传文件
        System.out.println("上传文件");
        String url = fileService.upload(file);
        return R.ok().put("data",url);
    }
}
