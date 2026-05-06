package com.obviously20.controller;

import cn.hutool.core.io.IoUtil;
import com.obviously20.pojo.User;
import com.obviously20.service.UserService;
import com.obviously20.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    //创建UserService实例：调用UserServiceImpl的构造方法
//    private UserService userService = new UserServiceImpl();//todo 但是这里耦合较高，所以一般不建议这样写，建议使用控制反转+依赖注入（进行解耦）


    //依赖注入（DI）：
    //第一种：1). 属性注入
    //- 优点：代码简洁、方便快速开发。
    //- 缺点：隐藏了类之间的依赖关系（UserService依赖于UserDao、UserController依赖于UserService）、可能会破坏类的封装性

    //2
    @Autowired//todo 依赖注入（DI）：自动将UserService的实例从Spring容器中注入给userService变量
    //@Qualifier("userServiceImpl2")//todo 指定要注入的实现类为UserServiceImpl2，细节：bean的名称为userServiceImpl2(具体实现类的名称=接口的名称且首字母小写)

    //3
//    @Resource(name = "userServiceImpl")
    private UserService userService;

//    //第二种：2). 构造方法注入
//    //- 优点：能清晰地看到类的依赖关系、提高了代码的安全性。
//    //- 缺点：代码繁琐、如果构造参数过多，可能会导致构造函数臃肿。
//    //- 注意：如果只有一个构造函数，@Autowired注解可以省略。（通常来说，也只有一个构造函数）
//    private final UserService userService;
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

//    //第三种：3). setter方法注入
//    //- 优点：保持了类的封装性，依赖关系更清晰。
//    //- 缺点：需要额外编写setter方法，增加了代码量。
//    private UserService userService;
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    /*todo 在项目开发中，基于@Autowired进行依赖注入时，基本都是第一种和第二种方式。
        （官方推荐第二种方式，因为会更加规范）但是在企业项目开发中，很多的项目中，
        也会选择第一种方式因为更加简洁、高效（在规范性方面进行了妥协）。*/



    @RequestMapping("/list")
    public List<User> list() {
//        //1.先从文本中读取用户信息
//        //this.getClass():获取当前类的Class对象
//        //this.getClass().getClassLoader().getResourceAsStream("/user.txt"):获取类加载器加载的资源流
//        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("user.txt");
//        //IoUtil.readLines(inputStream, StandardCharsets.UTF_8, new ArrayList<>()):读取流中的所有行，将每行内容添加到ArrayList中
//        ArrayList<String> list = IoUtil.readLines(inputStream, StandardCharsets.UTF_8, new ArrayList<>());
//
//        //2.将读取到的用户信息转换为User对象,并添加到List中
//        List<User> users = list.stream().map(line -> {
//            String[] user = line.split(",");
//            Integer id = Integer.parseInt(user[0]);
//            String username = user[1];
//            String password = user[2];
//            String name = user[3];
//            Integer age = Integer.parseInt(user[4]);
//            LocalDateTime updateTime = LocalDateTime.parse(user[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            return new User(id, username, password, name, age, updateTime);
//        }).collect(Collectors.toList());
//
//        //3.返回用户信息:json格式
//        return users;


        /*- 前端发起的请求，由Controller层接收（Controller响应数据给前端）
        - Controller层调用Service层来进行逻辑处理（Service层处理完后，把处理结果返回给Controller层）
        - Serivce层调用Dao层（逻辑处理过程中需要用到的一些数据要从Dao层获取）
        - Dao层操作文件中的数据（Dao拿到的数据会返回给Service层）

          所以在一个项目下要分Controller层、Service层、Dao层(包分别对应)，且每个层之间要通过接口进行通信：创建接口类 接口名 = new impl实例类();
        */

        List<User> users = userService.handleInformation();

        return users;

    }
}
