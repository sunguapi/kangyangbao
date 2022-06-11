package com.nbrt.kybao.mall.controller;

import com.nbrt.kybao.mall.dto.common.ApiResult;
import com.nbrt.kybao.mall.util.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hjh
 * @date 2022/6/1 19:17
 */
@CrossOrigin
@RestController
@RequestMapping("/system")
@Api(tags = "系统控制")
public class SystemController {

    @Autowired
    private OssUtil ossUtil;

    @ApiOperation("单张图片上传")
    @PostMapping("/upload/oss/one")
    public ApiResult<String> uploadOssFile(MultipartFile file) {
        if (file==null){
            ApiResult.failedWithMessage(500,"上传内容为空");
        }
        String image = ossUtil.uploadFile(file, "image");
        return ApiResult.successWithMessageAndData("上传成功",image);
    }

    @ApiOperation("多张图片上传")
    @PostMapping("/upload/oss/list")
    public ApiResult<List<String>> uploadOssFile(MultipartFile[] files) {
        if (files.length==0){
            ApiResult.failedWithMessage(500,"上传内容为空");
        }
        List<String> list=new ArrayList<>();
        for (MultipartFile file : files) {
            String url = ossUtil.uploadFile(file,"images");
            list.add(url);
        }
        return ApiResult.successWithMessageAndData("上传成功",list);
    }

}
