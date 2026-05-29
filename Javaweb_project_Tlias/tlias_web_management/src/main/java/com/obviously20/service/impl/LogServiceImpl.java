package com.obviously20.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.obviously20.mapper.OperateLogMapper;
import com.obviously20.pojo.LogOperationInformation;
import com.obviously20.pojo.LogQueryParam;
import com.obviously20.pojo.PageResult;
import com.obviously20.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 分页查询日志
     * @param logQueryParam
     * @return
     */
    @Override
    public PageResult<LogOperationInformation> page(LogQueryParam logQueryParam) {
        //1.先声明用pageHelper分页查询
        PageHelper.startPage(logQueryParam.getPage(), logQueryParam.getPageSize());

        //2.查询日志
        List<LogOperationInformation> list = operateLogMapper.selectList();

        //3.强转为Page
        Page<LogOperationInformation> page = (Page<LogOperationInformation>) list;

        //4.返回分页结果
        return new PageResult<>(page.getTotal(), page.getResult());
    }
}
