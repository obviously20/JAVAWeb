package com.obviously20.service;


import com.obviously20.pojo.Clazz;

import java.util.List;

public interface ClazzService {
    /**
     * 添加班级:该方法用于添加班级信息
     */
    void addClazz(Clazz clazz);

    /**
     * 查询所有班级:该方法用于查询所有班级信息
     */
    List<Clazz> selectAll();
}
