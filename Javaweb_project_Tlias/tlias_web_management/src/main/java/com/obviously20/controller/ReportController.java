package com.obviously20.controller;

import com.obviously20.pojo.JobOption;
import com.obviously20.pojo.Result;
import com.obviously20.pojo.StudentOption;
import com.obviously20.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计各个职位的员工人数
     */
    @GetMapping("/empJobData")
    public Result empJobData() {
        log.info("统计各个职位的员工人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result empGenderData() {
        log.info("统计各个性别员工员工人数");
        List<Map> empGenderData = reportService.getEmpGenderData();
        return Result.success(empGenderData);
    }


    /**
     * 学员学历统计
     * */
    @GetMapping("/studentDegreeData")
    public Result studentDegreeData() {
        log.info("统计学员学历");
        List<Map> studentDegreeData = reportService.getStudentDegreeData();
        return Result.success(studentDegreeData);
    }


    /**
     * 班级人数统计
     * */
    @GetMapping("/studentCountData")
    public Result studentCountData() {
        log.info("统计班级人数");
        StudentOption studentOption = reportService.getStudentCountData();
        return Result.success(studentOption);
    }




}
