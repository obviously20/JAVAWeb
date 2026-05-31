package com.aliyun.oss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(AliyunOSSProperties.class)//@EnableConfigurationProperties:将AliyunOSSProperties这个类加入到Spring容器中：因为该注解有用到@Import注解,而Import注解是将类加入到Spring容器中的注解
@Configuration
public class AliyunOSSAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean//指定在Spring容器中没有AliyunOSSOperator这个类时，才创建一个
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSProperties aliyunOSSProperties) {
        return new AliyunOSSOperator(aliyunOSSProperties);
    }

}
