package com.obviously20.mapper;

import com.obviously20.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*
     * 部门列表查询：查询所有部门列表
     * */

    //todo 由响应测试返回的数据可知：数据库中表中的字段名与pojo类中属性名一样的被Mybatis-Plus自动映射了
    // 但形如xxx_xxx 对应的 xxxXxx 没有被自动映射，需要配置
    // 方法1：用@Results和@Result配置
//    @Results({
//            @Result(column = "update_time",property = "updateTime"),
//            @Result(column = "create_time",property = "createTime")
//    })

    //todo 方法2:起别名
//    @Select("select id, name, create_time as createTime, update_time as updateTime from dept order by update_time desc")

    //todo 方法3:在Springboot中配置Mybatis开启驼峰命名(推荐)
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findDeptList();

    /*
     * 删除部门：根据id删除部门
     * */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /*
     * 添加部门：添加部门
     * */
    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    /**
     * 查询部门详情：根据id查询部门详情
     * */
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept selectById(Integer deptId);


    /**
     * 修改部门数据：根据id修改部门数据
     * */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void updateById(Dept dept);
}
