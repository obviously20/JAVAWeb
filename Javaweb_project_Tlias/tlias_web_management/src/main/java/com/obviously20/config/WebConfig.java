package com.obviously20.config;

import com.obviously20.interceptor.DemoInterceptor;
import com.obviously20.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration//配置类
public class WebConfig implements WebMvcConfigurer {

    @Autowired
//    private DemoInterceptor demoInterceptor;
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器:addInterceptor(demoInterceptor)
        //添加拦截路径:addPathPatterns("/**"):拦截所有路径
        //addPathPatterns("/login"):拦截/login路径
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");//排除/login路径(login路径不需要拦截)
    }

    // /*:拦截一级路径,例如:/login,/user不能匹配/depts/1
    // **:拦截所有路径,例如:/login,/user,/user/123

    //过滤器和拦截器之间的区别，其实它们之间的区别主要是两点：
    //- 接口规范不同：过滤器需要实现Filter接口，而拦截器需要实现HandlerInterceptor接口。
    //- 拦截范围不同：过滤器Filter会拦截所有的资源，而Interceptor只会拦截Spring环境中的资源。

}
