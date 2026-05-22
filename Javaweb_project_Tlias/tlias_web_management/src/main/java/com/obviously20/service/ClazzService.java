package com.obviously20.service;


import com.obviously20.pojo.Clazz;
import com.obviously20.pojo.ClazzQueryParam;
import com.obviously20.pojo.PageResult;

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

    /**
     * 班级列表查询
     * */
    PageResult<Clazz> selectClazz(ClazzQueryParam clazzQueryParam);

    /**
     * 删除班级
     * */
    void delete(Integer id);

    /**
     * 根据ID查询班级信息
     * */
    Clazz selectById(Integer id);

    /**
     * 修改班级信息
     * */
    void update(Clazz clazz);
}
