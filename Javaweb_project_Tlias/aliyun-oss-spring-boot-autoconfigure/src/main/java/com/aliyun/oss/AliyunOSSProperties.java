package com.aliyun.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "aliyun.oss")
//指定将application.yml文件中aliyun.oss前缀的属性，注入到AliyunOSSProperties类中（只有IOC容器才能使用这个注解，所以需要在AutoConfiguration类中加入@EnableConfigurationProperties注解）
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;
}
