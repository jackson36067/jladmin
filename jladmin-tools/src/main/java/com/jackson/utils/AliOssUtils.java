package com.jackson.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AliOssUtils {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * 通过alioss保存图片,并返回图片的地址
     *
     * @return
     */
    public String upload(MultipartFile image) {
        // 获取上传的文件的输入流
        InputStream inputStream = null;
        try {
            inputStream = image.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 避免文件覆盖
        String originalFilename = image.getOriginalFilename();
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);//参数分别表示上传到 OSS 的地方，文件名，上传的文件

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }
}
