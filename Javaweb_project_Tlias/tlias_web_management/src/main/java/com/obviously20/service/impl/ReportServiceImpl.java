package com.obviously20.service.impl;

import com.obviously20.mapper.EmpMapper;
import com.obviously20.pojo.JobOption;
import com.obviously20.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
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

    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countGenderNum();
    }
}
