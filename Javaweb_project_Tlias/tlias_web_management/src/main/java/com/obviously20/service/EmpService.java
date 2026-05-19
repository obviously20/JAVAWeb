package com.obviously20.service;

import com.obviously20.pojo.Emp;
import com.obviously20.pojo.EmpQueryParam;
import com.obviously20.pojo.PageResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {
    /**
     * 根据页数和每页显示条数查询员工数据
     * */
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
    //优化1：上面的请求参数太多了，太复杂了，所以这里可以定义一个EmpQueryParam类，来接收用户输入的查询参数
    PageResult<Emp> page(EmpQueryParam empQueryParam);


}
