package com.obviously20.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.obviously20.mapper.StudentMapper;
import com.obviously20.pojo.PageResult;
import com.obviously20.pojo.Student;
import com.obviously20.pojo.StudentQueryParam;
import com.obviously20.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询学生
     * */
    @Override
    public PageResult<Student> select(StudentQueryParam studentQueryParam) {
        //1.先声明一个PageResult对象，用于存储分页结果
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        //2.调用Mapper层方法，查询分页结果
        List<Student> list = studentMapper.select(studentQueryParam);
        //3.强转为Page
        Page<Student> page = (Page<Student>) list;
        //4.返回PageResult对象
        return new PageResult<>(page.getTotal(), page.getResult());

    }


    /**
     * 删除学员
     * */
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }


    /**
     * 添加学员
     * */
    @Override
    public void insert(Student student) {
        //1.设置createTime和updateTime为当前时间
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        //2.调用Mapper层方法，添加学员
        studentMapper.insert(student);
    }


    /**
     * 根据ID查询
     * */
    @Override
    public Student selectById(Integer id) {
        return studentMapper.selectById(id);
    }


    /**
     * 修改学员
     * */
    @Override
    public void update(Student student) {
        //1.设置updateTime为当前时间
        student.setUpdateTime(LocalDateTime.now());
        //2.调用Mapper层方法，修改学员
        studentMapper.update(student);
    }

    /**
     * 违纪处理
     * */
    @Override
    public void violation(Integer id, Integer score) {
        studentMapper.violation(id, score);
    }
}
