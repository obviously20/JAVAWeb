package com.obviously20.service.impl;

import com.obviously20.mapper.ClazzMapper;
import com.obviously20.pojo.Clazz;
import com.obviously20.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;


    /**
     * 添加班级:该方法用于添加班级信息
     */
    @Override
    public void addClazz(Clazz clazz) {
        //1.补全班级信息的创建时间和修改时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //2.调用班级映射层的方法添加班级信息
        clazzMapper.insert(clazz);
    }
}
