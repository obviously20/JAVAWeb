package com.obviously20.controller;

import com.obviously20.pojo.Emp;
import com.obviously20.pojo.EmpQueryParam;
import com.obviously20.pojo.PageResult;
import com.obviously20.pojo.Result;
import com.obviously20.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /*
    * 查询对应页数+每页显示条数的员工数据
    * */
    //@RequestParam(defaultValue = "1") Integer page,
    //@RequestParam(defaultValue = "10") Integer pageSize,
    //todo 设置默认值为1和10，避免用户没有输入时报错
    //todo: 处理日期格式@DateTimeFormat(pattern = "yyyy-MM-dd")/@DateTimeFormat(pattern = "yyyy/MM/dd")
    // 里面的日期格式根据接口文档确定
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//        log.info("page:{},pageSize:{},name:{},gender:{},begin:{},end:{}", page, pageSize,name,gender,begin,end);//输出请求参数
//        PageResult<Emp> pageResult = empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//    }

    //优化1：上面的请求参数太多了，太复杂了，所以这里可以定义一个EmpQueryParam类，来接收用户输入的查询参数
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("page:{},pageSize:{}", empQueryParam.getPage(), empQueryParam.getPageSize());//输出请求参数
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }


    /**
     * 添加员工
     * */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("添加员工save:{}", emp);
        empService.save(emp);
        return Result.success();
    }

}
