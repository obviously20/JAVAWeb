package com.obviously20.service;

import com.obviously20.pojo.Dept;

import java.util.List;

public interface DeptService {
    //部门列表查询
    List<Dept> findDeptList();

    //删除部门
    void deleteDeptById(Integer id);

    /*
     * 添加部门
     * */
    void addDept(Dept dept);

    /**
     * 查询部门详情：根据id查询部门详情
     * */
    Dept getById(Integer deptId);

    /**
     * 修改部门数据
     * */
    void updateDept(Dept dept);
}
