package com.obviously20.controller;

import com.obviously20.pojo.Result;
import com.obviously20.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Slf4j
@RestController
public class UploadController {

//    /**
//     * 上传文件：
//     * @param username 用户名
//     * @param age 年龄
//     * @param file 上传的文件（MultipartFile类型 名字（要与前端上传的文件名一致）：file）
//     */

    //-------------------------------------- 上传文件到本地--------------------------------------

//    //user.dir 是 Java 虚拟机（JVM）启动时的当前工作目录，也就是 JVM 启动时所在的目录
//    //当您使用 System.getProperty("user.dir") 时：
//    //如果您在项目根目录（tlias_web_management）启动应用，它会返回类似 "D:\JAVA_learning\JAVAWeb\Javaweb_project_Tlias\tlias_web_management" 的路径
//    //然后加上 "/src/main/resources/static/uploads/"，最终形成完整的上传目录路径
//    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";
//
//    @RequestMapping("/upload")
//    public Result upload(String username, Integer age , MultipartFile file) throws Exception {
//        log.info("username: {}, age: {}, file: {}", username, age, file);
//        if (!file.isEmpty()) {
//            // 生成唯一文件名
//            // 获取原始文件名
//            String originalFilename = file.getOriginalFilename();
//            // 提取文件扩展名（.jpg、.png等），其中lastIndexOf()方法返回最后一个字符的索引（从0开始计数），substring()方法返回从指定索引开始的子字符串
//            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
//            // 生成唯一文件名，将UUID转换为字符串并替换所有连字符（-），最后添加文件扩展名
//            // 例如：1234567890abcdef.jpg
//            String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + extName;
//            // 拼接完整的文件路径
//            File targetFile = new File(UPLOAD_DIR + uniqueFileName);
//
//            // 如果目标目录不存在，则创建它
//            //.getParentFile()方法返回文件的父目录（即包含文件的目录），exists()方法检查目录是否存在
//            if (!targetFile.getParentFile().exists()) {
//                targetFile.getParentFile().mkdirs();
//            }
//            // 保存文件到指定路径 file.transferTo()方法将文件内容写入目标文件
//            file.transferTo(targetFile);
//        }
//        return Result.success();
//    }

    //-------------------------------------- 上传文件到阿里云OSS--------------------------------------

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("file: {}", file);
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(url);
    }

}
