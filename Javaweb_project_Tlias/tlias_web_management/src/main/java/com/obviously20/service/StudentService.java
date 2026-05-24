package com.obviously20.service;

import com.obviously20.pojo.PageResult;
import com.obviously20.pojo.Student;
import com.obviously20.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 分页查询学生
     * */
    PageResult<Student> select(StudentQueryParam studentQueryParam);


    /**
     * 删除学员
     * */
    void delete(List<Integer> ids);


    /**
     * 添加学员
     * */
    void insert(Student student);


    /**
     * 根据ID查询
     * */
    Student selectById(Integer id);


    /**
     * 修改学员
     * */
    void update(Student student);


    /**
     * 违纪处理
     * */
    void violation(Integer id, Integer score);
}
