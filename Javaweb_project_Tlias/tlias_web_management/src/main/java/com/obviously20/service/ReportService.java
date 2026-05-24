package com.obviously20.service;


import com.obviously20.pojo.JobOption;
import com.obviously20.pojo.StudentOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    /**
     * 统计各个职位的员工人数
     * */
    JobOption getEmpJobData();

    /**
     * 统计各个性别员工员工人数
     * */
    List<Map> getEmpGenderData();

    /**
     * 统计学员学历
     * */
    List<Map> getStudentDegreeData();

    /**
     * 统计班级人数
     * */
    StudentOption getStudentCountData();
}
