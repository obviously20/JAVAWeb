package com.obviously20.Dao.impl;

import cn.hutool.core.io.IoUtil;
import com.obviously20.Dao.UserDao;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

//todo 在每个实现类上添加@Component注解，将它们注册为Spring容器中的组件，交给Spring容器管理（IOC）--自动创建和管理
//todo ioc中创建和管理的组件（对象），称作Bean（Bean对象）
//@Component//todo 控制反转：将对象的创建和管理交给Spring容器，而不是在代码中手动创建和管理（即将原本在代码中创建的对象的控制权交给Spring容器管理）
@Repository


public class UserDaoImpl implements UserDao {

    @Override
    public ArrayList<String> findAllUsersInformation() {
        //1.先从文本中读取用户信息
        //this.getClass():获取当前类的Class对象
        //this.getClass().getClassLoader().getResourceAsStream("/user.txt"):获取类加载器加载的资源流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        //IoUtil.readLines(inputStream, StandardCharsets.UTF_8, new ArrayList<>()):读取流中的所有行，将每行内容添加到ArrayList中
        ArrayList<String> list = IoUtil.readLines(inputStream, StandardCharsets.UTF_8, new ArrayList<>());

        return list;
    }
}
