package com.obviously20.controller;

import com.obviously20.anno.Log;
import com.obviously20.pojo.PageResult;
import com.obviously20.pojo.Result;
import com.obviously20.pojo.Student;
import com.obviously20.pojo.StudentQueryParam;
import com.obviously20.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    /**
     * 分页查询学生
     * */
    @GetMapping
    public Result select(StudentQueryParam studentQueryParam) {
        log.info("studentQueryParam:{}", studentQueryParam);
        PageResult<Student> studentsPageResult = studentService.select(studentQueryParam);
        return Result.success(studentsPageResult);
    }


    /**
     * 删除学员
     * */
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        log.info("ids:{}", ids);
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(str -> Integer.valueOf(str))//将字符串转换为整数["1","2","3"]]->[1,2,3]
                .collect(Collectors.toList());//将流转换为列表
        studentService.delete(idList);
        return Result.success();
    }


    /**
     * 添加学员
     * */
    @Log
    @PostMapping
    public Result insert(@RequestBody Student student) {
        log.info("添加student:{}", student);
        studentService.insert(student);
        return Result.success();
    }


    /**
     * 根据ID查询
     * */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("根据ID查询学员:{}", id);
        Student student = studentService.selectById(id);
        return Result.success(student);
    }


    /**
     * 修改学员
     **/
    @Log
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员信息:{}", student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 违纪处理
     * */
    @Log
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("id:{},score:{}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }









}
