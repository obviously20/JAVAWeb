package com.obviously20;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpRequest;

@RestController
public class RequestController {

    /*@RequestMapping(value = "/request", method = RequestMethod.POST)
    public String request(@RequestBody String body) {
        System.out.println("请求体: " + body);
        return "OK";
    }*/

    @RequestMapping("/request")
    public String request(HttpServletRequest request) throws IOException {

        //获取请求行中的请求方式GET/POST
        String method = request.getMethod();
        System.out.println(method);//GET

        //获取请求行中的请求路径url：http://localhost:8080/request
        String url = request.getRequestURL().toString();
        System.out.println(url);
        //获取uri
        String uri = request.getRequestURI().toString();
        System.out.println(uri);//request

        //获取请求参数中的name
        String name = request.getParameter("name");
        System.out.println(name);
        //其他请求参数同理
        String age = request.getParameter("age");
        System.out.println(age);

        //获取请求头
        String header = request.getHeader("User-Agent");
        System.out.println(header);//Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36
        String header2 = request.getHeader("Cookie");
        System.out.println(header2);//null
        //其他请求头同理

        //获取请求体(POST请求才会有请求体)
        String body = request.getReader().readLine();
        System.out.println(body);//null


        return "OK";
    }
}
