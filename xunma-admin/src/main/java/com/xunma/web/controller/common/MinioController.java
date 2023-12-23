package com.xunma.web.controller.common;
import com.xunma.common.utils.minio.MinioConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
 
import javax.servlet.http.HttpServletResponse;
import java.util.List;
 
@RestController
@CrossOrigin
@RequestMapping("/test")
public class MinioController {
 
    @Autowired
    MinioConfig minioConfig;
 
    // 上传
    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        return this.minioConfig.putObject(multipartFile);
    }
 
    // 下载文件
    @GetMapping("/download")
    public void download(@RequestParam("fileName")String fileName, HttpServletResponse response) {
        this.minioConfig.download(fileName,response);
    }
 
    // 列出所有存储桶名称
    @PostMapping("/list")
    public List<String> list() throws Exception {
        return this.minioConfig.listBucketNames();
    }
 
    // 创建存储桶
    @PostMapping("/createBucket")
    public boolean createBucket(String bucketName) throws Exception {
        return this.minioConfig.makeBucket(bucketName);
    }
 
    // 删除存储桶
    @PostMapping("/deleteBucket")
    public boolean deleteBucket(String bucketName) throws Exception {
        return this.minioConfig.removeBucket(bucketName);
    }
 
    // 列出存储桶中的所有对象名称
    @PostMapping("/listObjectNames")
    public List<String> listObjectNames(String bucketName) throws Exception {
        return this.minioConfig.listObjectNames(bucketName);
    }
 
    // 删除一个对象
    @PostMapping("/removeObject")
    public boolean removeObject(String bucketName, String objectName) throws Exception {
        return this.minioConfig.removeObject(bucketName, objectName);
    }
 
    // 文件访问路径
    @PostMapping("/getObjectUrl")
    public String getObjectUrl(String bucketName, String objectName) throws Exception {
        return this.minioConfig.getObjectUrl(bucketName, objectName);
    }
}
 
 
 