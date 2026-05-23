package com.obviously20.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.obviously20.exception.BusinessException;
import com.obviously20.mapper.ClazzMapper;
import com.obviously20.pojo.Clazz;
import com.obviously20.pojo.ClazzQueryParam;
import com.obviously20.pojo.PageResult;
import com.obviously20.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<Clazz> selectAll() {
        return clazzMapper.selectAll();
    }

    @Override
    public PageResult<Clazz> selectClazz(ClazzQueryParam clazzQueryParam) {
        //1.声明分页结果对象
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //2.调用班级映射层的方法查询班级信息
        List<Clazz> clazzList = clazzMapper.selectClazz(clazzQueryParam);

        //3.判断状态status
        clazzList.forEach(clazz -> {
            //如果结课时间在现在之前,则状态为已结束
            if (clazz.getEndDate().isBefore(LocalDate.now())) {
                clazz.setStatus("已结束");
            } else if (clazz.getBeginDate().isAfter(LocalDate.now())) {//如果开课时间在现在之后,则状态为未开始
                clazz.setStatus("未开始");
            } else {//否则状态为进行中
                clazz.setStatus("进行中");
            }
        });

        //4.强转
        Page<Clazz> page = (Page<Clazz>) clazzList;
        //5.返回分页结果
        return new PageResult<Clazz>(page.getTotal(), page.getResult());

    }


    /**
     * 删除班级:该方法用于删除班级信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 开启事务
    public void delete(Integer id) {
        //1.先判断班级中是否还有学生
        Integer studentCount = clazzMapper.selectStudentCount(id);
        if (studentCount > 0) {
            throw new BusinessException("班级中还有学生，不能删除");
        }

        //2.到这里,说明班级中没有学生,可以删除班级信息
        clazzMapper.delete(id);

    }

    @Override
    public Clazz selectById(Integer id) {
        return clazzMapper.selectById(id);
    }

    @Override
    public void update(Clazz clazz) {
        //1.补全班级信息的修改时间
        clazz.setUpdateTime(LocalDateTime.now());
        //2.调用班级映射层的方法修改班级信息
        clazzMapper.update(clazz);
    }
}
