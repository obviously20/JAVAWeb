package com.obviously20.service.impl;

import com.obviously20.Dao.DeptDao;
import com.obviously20.pojo.Dept;
import com.obviously20.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Dept> handleDeptData() {

        List<String> lines = deptDao.findAllDeptName();

        //2. 对原始数据进行处理 , 组装部门数据
        List<Dept> deptList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String name = parts[1];
            LocalDateTime updateTime = LocalDateTime.parse(parts[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new Dept(id, name, updateTime);
        }).toList();

        return deptList;
    }
}
