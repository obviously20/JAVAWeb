package com.obviously20.controller;

import com.obviously20.anno.Log;
import com.obviously20.pojo.Clazz;
import com.obviously20.pojo.ClazzQueryParam;
import com.obviously20.pojo.PageResult;
import com.obviously20.pojo.Result;
import com.obviously20.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
    /**
     * 添加班级:该接口用于添加班级信息
     */
    @Log
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz){
        log.info("添加班级信息:{}",clazz);
        clazzService.addClazz(clazz);
        return Result.success();
    }

    /**
     * 查询所有班级:该接口用于查询所有班级信息
     */
    @GetMapping("/list")
    public Result listClazz(){
        log.info("查询所有班级信息");
        List<Clazz> clazzList = clazzService.selectAll();
        return Result.success(clazzList);
    }


    /**
     * 班级列表查询
     * */
    @GetMapping
    public Result selectClazz(ClazzQueryParam clazzQueryParam){
        log.info("查询班级信息:{}",clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.selectClazz(clazzQueryParam);
        return Result.success(pageResult);
    }


    /**
     * 删除班级:该接口用于删除班级信息
     * */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级信息:{}",id);
        clazzService.delete(id);
        return Result.success();
    }

    //修改班级信息
    /**
     * 根据ID查询
     * */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("根据ID查询班级信息:{}",id);
        Clazz clazz = clazzService.selectById(id);
        return Result.success(clazz);
    }
    /**
     * 修改班级信息
     * */
    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级信息:{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }


}
