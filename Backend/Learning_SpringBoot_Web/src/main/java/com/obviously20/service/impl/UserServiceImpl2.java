package com.obviously20.service.impl;

import com.obviously20.Dao.UserDao;
import com.obviously20.pojo.User;
import com.obviously20.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Component
//todo 而Spring框架为了更好的标识web应用程序开发当中，bean对象到底归属于哪一层，又提供了@Component的衍生注解：@Service,@Repository,@Component,@Controller
// 那么此时，我们就可以使用 @Service 注解声明Service层的bean。 使用 @Repository 注解声明Dao层的bean。
// 而还有些不属于Controller、Service、Dao层的bean，比如工具类、配置类等，我们可以使用@Component注解声明。
// 注意：1.在Springboot集成web开发中，声明控制器bean只能用@Controller。
//      2.声明bean的注解要想生效，需要被扫描到（@ComponentScan），启动类默认扫描当前包及其子包
@Service
public class UserServiceImpl2 implements UserService {

//    private UserDao userDao = new UserDaoImpl();

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> handleInformation() {

        ArrayList<String> list = userDao.findAllUsersInformation();

        List<User> users = list.stream().map(line -> {
            String[] user = line.split(",");
            Integer id = Integer.parseInt(user[0]);
            String username = user[1];
            String password = user[2];
            String name = user[3];
            Integer age = Integer.parseInt(user[4]);
            LocalDateTime updateTime = LocalDateTime.parse(user[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id+200, username, password, name, age, updateTime);
        }).collect(Collectors.toList());

        return users;
    }
}
