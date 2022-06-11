package com.nbrt.kybao.mall.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.nbrt.kybao.mall.model.entity.OssParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author hjh
 * @date 2022/6/1 17:04
 */
@Slf4j
@Component
public class OssUtil {
    @Resource
    private OssParam ossParam;

    private String bucketName, endpoint, accessKeyId, accessKeySecret;

    public String uploadFile(MultipartFile multipartFile, String directory) {
        {
            //获取oss上传配置文件中的参数
            bucketName = ossParam.getBucketName();
            endpoint = ossParam.getEndpoint();
            accessKeyId = ossParam.getAccessKeyId();
            accessKeySecret = ossParam.getAccessKeySecret();
        }
        // 1 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 2 获取文件上传的流
            InputStream inputStream = multipartFile.getInputStream();

            // 3 构建日期目录
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String dataPath = dateFormat.format(new Date());

            // 4 修改文件名
            String originalFilename = multipartFile.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            assert originalFilename != null;
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = fileName + suffix;
            String fileUrl = directory + "/" + dataPath + "/" + newFilename;

            // 5 文件上传到阿里云服务器
            ossClient.putObject(bucketName, fileUrl, inputStream);
            return "https://" + bucketName + "." + endpoint + "/" + fileUrl;
        } catch (Exception e) {
            log.error("文件上传失败", e);
            throw new RuntimeException("文件上传oss失败");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public void downLoadFile(String yuan, File muDi) {
        {
            //获取oss上传配置文件中的参数
            bucketName = ossParam.getBucketName();
            endpoint = ossParam.getEndpoint();
            accessKeyId = ossParam.getAccessKeyId();
            accessKeySecret = ossParam.getAccessKeySecret();
        }
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 下载Object到本地文件，并保存到指定的本地路径中。如果指定的本地文件存在会覆盖，不存在则新建。
        // 如果未指定本地路径，则下载后的文件默认保存到示例程序所属项目对应本地路径中。
        File muDiFile = new File(muDi, System.currentTimeMillis() + (yuan.substring(yuan.indexOf("."))));
        ossClient.getObject(new GetObjectRequest(bucketName, yuan), muDiFile);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
