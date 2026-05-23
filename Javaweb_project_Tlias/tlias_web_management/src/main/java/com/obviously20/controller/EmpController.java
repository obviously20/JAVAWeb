package com.obviously20.controller;

import com.obviously20.pojo.*;
import com.obviously20.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
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


    /**
     * 查询全部员工
     * */
    @GetMapping("/list")
    public Result list(){
        log.info("查询全部员工list");
        List<EmpVo> emps = empService.list();
        return Result.success(emps);
    }

    /**
     * 批量删除员工（根据id列表）：且单个删除员工是批量删除的一种特殊情况
     * */
//    //用数组接受id列表
//    @DeleteMapping
//    public Result delete(Integer[] ids){
//        log.info("批量删除员工delete:{}", Arrays.toString(ids));
//        return Result.success();
//    }

    //todo 但为了更好的操作数据，企业一般会使用List<Integer>来接收id列表
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除员工delete:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id查询员工详情
     * */
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id) {
        log.info("根据id查询员工详情select:{}", id);
        Emp emp = empService.selectById(id);
        return Result.success(emp);
    }

    /**
     * 修改员工
     * */
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工update:{}", emp);
        empService.update(emp);
        return Result.success();
    }



}
