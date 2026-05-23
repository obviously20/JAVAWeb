package com.obviously20.service;

import com.obviously20.pojo.Emp;
import com.obviously20.pojo.EmpQueryParam;
import com.obviously20.pojo.EmpVo;
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

    /**
     * 添加员工
     * */
    void save(Emp emp);

    /**
     * 查询全部员工
     * */
    List<EmpVo> list();

    /**
     * 批量删除员工（根据id列表）
     * */
    void delete(List<Integer> ids);


    /**
     * 根据id查询员工详情
     * */
    Emp selectById(Integer id);

    /**
     * 修改员工
     * */
    void update(Emp emp);
}
