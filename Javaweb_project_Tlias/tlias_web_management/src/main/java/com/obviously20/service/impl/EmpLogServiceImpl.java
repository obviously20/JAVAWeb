package com.obviously20.service.impl;

import com.obviously20.mapper.EmpLogMapper;
import com.obviously20.pojo.EmpLog;
import com.obviously20.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    //todo 我们要想控制事务的传播行为，在@Transactional注解的后面指定一个属性propagation，通过 propagation 属性来指定传播行为
    //      REQUIRES_NEW: 以新事务执行，如果当前有事务，则挂起当前事务，等待新事务完成后再执行
    //      默认值是REQUIRED，如果当前没有事务，则创建一个事务，如果有事务，则加入当前事务
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }

    /*todo 事务四大特性
        面试题：事务有哪些特性？
        - 原子性（Atomicity）：事务是不可分割的最小单元，要么全部成功，要么全部失败。
        - 一致性（Consistency）：事务完成时，必须使所有的数据都保持一致状态。
        - 隔离性（Isolation）：数据库系统提供的隔离机制，保证事务在不受外部并发操作影响的独立环境下运行。
        - 持久性（Durability）：事务一旦提交或回滚，它对数据库中的数据的改变就是永久的。
        事务的四大特性简称为：ACID*/
}
