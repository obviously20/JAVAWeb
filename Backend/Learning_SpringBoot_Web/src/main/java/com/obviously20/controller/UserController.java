package com.obviously20.controller;

import cn.hutool.core.io.IoUtil;
import com.obviously20.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController//里面包含@RestController和@ResponseBody，ResponseBody注解用于将方法返回值若为对象/集合等，转换为json格式
public class UserController {

    @RequestMapping("/list")
    public List<User> list() {
        //1.先从文本中读取用户信息
        //this.getClass():获取当前类的Class对象
        //this.getClass().getClassLoader().getResourceAsStream("/user.txt"):获取类加载器加载的资源流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        //IoUtil.readLines(inputStream, StandardCharsets.UTF_8, new ArrayList<>()):读取流中的所有行，将每行内容添加到ArrayList中
        ArrayList<String> list = IoUtil.readLines(inputStream, StandardCharsets.UTF_8, new ArrayList<>());

        //2.将读取到的用户信息转换为User对象,并添加到List中
        List<User> users = list.stream().map(line -> {
            String[] user = line.split(",");
            Integer id = Integer.parseInt(user[0]);
            String username = user[1];
            String password = user[2];
            String name = user[3];
            Integer age = Integer.parseInt(user[4]);
            LocalDateTime updateTime = LocalDateTime.parse(user[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id, username, password, name, age, updateTime);
        }).collect(Collectors.toList());

        //3.返回用户信息:json格式
        return users;

    }
}
