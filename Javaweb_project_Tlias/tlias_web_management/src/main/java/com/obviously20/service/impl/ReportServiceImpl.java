package com.obviously20.service.impl;

import com.obviously20.mapper.EmpMapper;
import com.obviously20.mapper.StudentMapper;
import com.obviously20.pojo.JobOption;
import com.obviously20.pojo.StudentOption;
import com.obviously20.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 统计各个职位的员工人数
     * */
    @Override
    public JobOption getEmpJobData() {
        // 调用Mapper方法获取职位人数统计
        List<Map<String,Object>> empPoslist = empMapper.countPosNum();
        // 提取职位和人数列表
        List<Object> posList = empPoslist.stream().map(item -> item.get("pos")).toList();
        List<Object> numList = empPoslist.stream().map(item -> item.get("num")).toList();
        // 创建JobOption对象
        return new JobOption(posList,numList);
    }

    /**
     * 统计各个性别员工员工人数
     * */
    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countGenderNum();
    }

    /**
     * 统计学员学历
     * */
    @Override
    public List<Map> getStudentDegreeData() {
        return studentMapper.countDegreeNum();
    }

    @Override
    public StudentOption getStudentCountData() {
        // 调用Mapper方法获取班级人数统计
        List<Map<String,Object>> clazzNumlist = studentMapper.countClazzNum();
        // 提取班级和人数列表
        List<Object> clazzList = clazzNumlist.stream().map(item -> item.get("clazz")).toList();
        List<Object> numList = clazzNumlist.stream().map(item -> item.get("num")).toList();
//        System.out.println("班级列表：" + clazzList);
//        System.out.println("人数列表：" + numList);
        // 创建StudentOption对象
        return new StudentOption(clazzList,numList);
    }
}
