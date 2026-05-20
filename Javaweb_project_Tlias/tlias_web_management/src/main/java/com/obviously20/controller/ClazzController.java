package com.obviously20.controller;

import com.obviously20.pojo.Clazz;
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


}
