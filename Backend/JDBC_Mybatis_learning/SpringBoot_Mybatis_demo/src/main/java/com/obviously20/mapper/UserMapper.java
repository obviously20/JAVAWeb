package com.obviously20.mapper;

import com.obviously20.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper//应用程序在运行时会自动为连接口创建实现类对象（代理对象UserMapperImpl），并将该对象注入到Spring容器中-bean
public interface UserMapper {

    @Select("select * from user")
    public List<User> findAll();

    @Delete("delete from user where id = #{id}")
    //运行之后，我们发现，#{...} 占位符，其实最终被替换成了 ？占位符，生成的是预编译的SQL语句。【推荐】
    //原因：预编译的SQL语句可以防止SQL注入攻击，提高数据库的安全性。
    //而${}占位符，是直接将参数值插入到SQL语句中，没有进行任何的处理，存在SQL注入攻击的风险。
    //所以，不推荐使用${}占位符。
    public Integer deleteById(Integer id);

}
