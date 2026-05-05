package com.obviously20.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data//自动添加getter和setter方法
@AllArgsConstructor//自动添加全参构造方法
@NoArgsConstructor//自动添加无参构造方法
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private LocalDateTime updateTime;
}
