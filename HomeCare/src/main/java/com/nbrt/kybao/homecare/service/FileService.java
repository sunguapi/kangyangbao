package com.nbrt.kybao.homecare.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    //文件上传
    public String upload(MultipartFile file);
}
