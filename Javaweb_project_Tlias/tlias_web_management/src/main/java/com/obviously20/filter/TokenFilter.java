package com.obviously20.filter;

import com.obviously20.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //强转为HttpServletRequest和HttpServletResponse
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.先获取请求路径
        String requestURI = request.getRequestURI();// /login

        //2.判断请求路径是否是包含登录路径,如果是的话就放行
        if (requestURI.contains("/login")) {
            //进入到这里,说明是登录路径,直接放行
            log.info("登录请求 , 直接放行");
            filterChain.doFilter(request, response);
            return;
        }

        //3.到这里,说明不是登录路径,需要判断是否有token
        //从请求头中获取token
        String token = request.getHeader("token");
        //不存在token,说明用户没有登录,需要跳转到登录页（返回401状态码）
        if (token == null || token.isEmpty()) {
            //说明用户没有登录,需要跳转到登录页（返回401状态码）
            log.info("用户没有登录,需要跳转到登录页（返回401状态码）");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);//sendError方法设置响应状态码为401，同时返回错误信息
            return;
        }

        //到这里,说明有token,需要验证token是否有效
        //验证token是否有效
        try{
            Claims claims = JwtUtil.parseToken("my_secret_key_that_is_at_least_32_bytes_long_for_security", token);
            //说明token有效,需要放行
            log.info("token有效,需要放行");
            filterChain.doFilter(request, response);
            return;
        }catch (Exception e){//如果令牌过期了/被篡改了，捕获异常，返回401状态码
            log.info("token无效,需要跳转到登录页（返回401状态码）");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);//sendError方法设置响应状态码为401，同时返回错误信息
            return;
        }


        /*todo Filter详解
        *  1.过滤器的执行流程
            * Filter放行前逻辑->放行->资源->放行后逻辑
           2.配置的过滤器的拦截路径/* 与/emps/* 分别代表什么意思?
              /*:表示拦截所有资源
              /emps/*:表示目录拦截，拦截/emps下的所有资源
           3.什么是过滤器链?
            项目中的多个过滤器就形成了一个过滤器链：而每个过滤器都有自己的放行前逻辑和放行后逻辑
            然后过滤器是按照类（eg:ATFilter>BFilter>CFilter）的加载顺序进行执行的，
            通过A->B的逻辑->C的逻辑->放行->资源->放行后逻辑->C->B->A的逻辑执行过滤器链
         */

    }
}
