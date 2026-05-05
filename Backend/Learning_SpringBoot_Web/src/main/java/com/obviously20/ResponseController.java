package com.obviously20;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;

@RestController
public class ResponseController {

    /*响应状态码 和 响应头如果没有特殊要求的话，通常不手动设定。服务器会根据请求处理的逻辑，自动设置响应状态码和响应头。*/

    //响应方法1：使用HttpServletResponse设置响应状态码和响应头，再使用getWriter()方法写响应体
    @RequestMapping("/response")
    public void response(HttpServletResponse response) throws IOException {
        //设置响应状态码
        response.setStatus(HttpServletResponse.SC_OK);
        //设置响应头
        response.setHeader("Content-Type", "text/plain; charset=utf-8");
        response.setHeader("name", "obviously20");
        //设置响应体
        response.getWriter().write("<h1>Hello, World!</h1>");
    }


    //响应方法2：使用ResponseEntity设置响应状态码和响应头，再使用body()方法写响应体
    @RequestMapping("/response2")
    public ResponseEntity<String> response2(HttpServletResponse response) throws IOException {
        return ResponseEntity.status(HttpServletResponse.SC_OK)//设置响应状态码
                .header("name", "obviously20")//设置响应头
                .body("<h1>Hello, World!-2</h1>");//设置响应体
    }


}
