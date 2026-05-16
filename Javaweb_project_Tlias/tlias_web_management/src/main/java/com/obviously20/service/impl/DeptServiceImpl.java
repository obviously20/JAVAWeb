package com.obviously20.service.impl;

import com.obviously20.mapper.DeptMapper;
import com.obviously20.pojo.Dept;
import com.obviously20.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findDeptList() {
        return deptMapper.findDeptList();
    }

    @Override
    public void deleteDeptById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void addDept(Dept dept) {
        //1.添加部门-->补全createTime和updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2.添加部门-->调用Mapper中的insert方法
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer deptId) {
        return deptMapper.selectById(deptId);
    }

    @Override
    public void updateDept(Dept dept) {
        //1.修改部门-->补全updateTime
        dept.setUpdateTime(LocalDateTime.now());
        //2.修改部门-->调用Mapper中的updateById方法
        deptMapper.updateById(dept);
    }
}
