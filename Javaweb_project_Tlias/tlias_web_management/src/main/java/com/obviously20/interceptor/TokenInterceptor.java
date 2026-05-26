package com.obviously20.interceptor;

import com.obviously20.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    //ctrl+o:重写方法的选择
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        //1.先获取请求路径
//        String requestURI = request.getRequestURI();// /login
//
//        //2.判断请求路径是否是包含登录路径,如果是的话就放行
//        if (requestURI.contains("/login")) {
//            //进入到这里,说明是登录路径,直接放行
//            log.info("登录请求 , 直接放行");
//            return true;
//        }

        //3.到这里,说明不是登录路径,需要判断是否有token
        //从请求头中获取token
        String token = request.getHeader("token");
        //不存在token,说明用户没有登录,需要跳转到登录页（返回401状态码）
        if (token == null || token.isEmpty()) {
            //说明用户没有登录,需要跳转到登录页（返回401状态码）
            log.info("用户没有登录,需要跳转到登录页（返回401状态码）");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);//sendError方法设置响应状态码为401，同时返回错误信息
            return false;
        }

        //到这里,说明有token,需要验证token是否有效
        //验证token是否有效
        try{
            Claims claims = JwtUtil.parseToken("my_secret_key_that_is_at_least_32_bytes_long_for_security", token);
            //说明token有效,需要放行
            log.info("token有效,需要放行");
            return true;
        }catch (Exception e){//如果令牌过期了/被篡改了，捕获异常，返回401状态码
            log.info("token无效,需要跳转到登录页（返回401状态码）");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);//sendError方法设置响应状态码为401，同时返回错误信息
            return false;
        }
    }
}
