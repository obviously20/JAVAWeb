package com.obviously20.service.impl;

import com.obviously20.Dao.UserDao;
import com.obviously20.pojo.User;
import com.obviously20.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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


//todo 如果有多个实现类，Spring容器就会报错，因为Spring容器不知道要注入哪个实现类。
// 解决方法1：在实现类上添加@Primary注解，将它们标记为首选实现类。
@Primary
//todo 解决方法2：结合@Autowired注解+@Qualifier注解，指定要注入的实现类。--在UserController中看
//todo 解决方法3：使用@Resource注解，是按照bean的名称进行注入。通过name属性指定要注入的bean的名称。--在UserController中看
//@Autowird 与 @Resource的区别
//- @Autowired 是spring框架提供的注解，而@Resource是JDK提供的注解
//- @Autowired 默认是按照类型注入，而@Resource是按照名称注入

public class UserServiceImpl implements UserService {

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
            return new User(id, username, password, name, age, updateTime);
        }).collect(Collectors.toList());

        return users;
    }
}
