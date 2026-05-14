package com.obviously20.mapper;

import com.obviously20.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//应用程序在运行时会自动为连接口创建实现类对象（代理对象UserMapperImpl），并将该对象注入到Spring容器中-bean
public interface UserMapper {

//    @Select("select * from user")
    //todo 用了xml文件映射配置中的方法，所以这里注释掉，一个接口方法对应的SQL语句，要么使用注解配置，要么使用XML配置，切不可同时配置
    public List<User> findAll();



    @Delete("delete from user where id = #{id}")
    //运行之后，我们发现，#{...} 占位符，其实最终被替换成了 ？占位符，生成的是预编译的SQL语句。【推荐】
    //原因：预编译的SQL语句可以防止SQL注入攻击，提高数据库的安全性。
    //而${}占位符，是直接将参数值插入到SQL语句中，没有进行任何的处理，存在SQL注入攻击的风险。
    //所以，不推荐使用${}占位符。
    public Integer deleteById(Integer id);



    @Insert("insert into user(username, password, name, age) values(#{username}, #{password}, #{name}, #{age})")
    //如果有多个要插入的字段，可以将多个字段封装到一个对象中
    //将User对象的属性值，插入到user表中，在方法中，需要将User对象作为参数传递进来，然后，MyBatis会自动将User对象的属性值，插入到SQL语句中
    public Integer insert(User user);
    /*工作原理：
    参数传递：当你调用 userMapper.insert(user) 时，User 对象作为参数传入

    属性映射：MyBatis 会通过 反射机制 读取 User 对象的属性值

    #{username} → 获取 user.getUsername() 的值
    #{password} → 获取 user.getPassword() 的值 -
    #{name} → 获取 user.getName() 的值
    #{age} → 获取 user.getAge() 的值
    预编译 SQL：MyBatis 会将 #{} 替换为 JDBC 的 ? 占位符，生成预编译的 SQL

    sql
    Insert Into File
    insert into user(username, password, name, age) values(?, ?, ?, ?)
    设置参数：将 User 对象的属性值按顺序设置到 PreparedStatement 中

    执行插入：执行 SQL 并返回受影响的行数*/



    @Update("update user set username = #{username}, password = #{password}, name = #{name}, age = #{age} where id = #{id}")
    public Integer update(User user);




    @Select("select * from user where name=#{name} and password=#{password}")
    public User findByNameAndPassword(@Param("name") String name, @Param("password") String password);
    //根据用户名和密码查询用户信息，@Param注解用于将方法参数绑定到SQL语句中的占位符上
    //@Param("name") 表示将方法参数 name 绑定到 SQL语句中的占位符 #{name}
    //因为方法参数中，有多个参数，且非SpringBoot骨架工程，所以，需要使用@Param注解，将每个参数绑定到SQL语句中的占位符上。
    //基于官方骨架创建的springboot项目中，接口编译时会保留方法形参名，@Param注解可以省略 (#{形参名})。


//    update user set password = ? , name = ?, age = ? where id = ? ;
    public Integer updateById(@Param("password") String password, @Param("name") String name,@Param("age") Integer age, @Param("id") Integer id);

}
