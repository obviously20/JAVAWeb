package com.obviously20.service;

import com.obviously20.pojo.LogOperationInformation;
import com.obviously20.pojo.LogQueryParam;
import com.obviously20.pojo.PageResult;

public interface LogService {

    /**
     * 分页查询日志
     * @param logQueryParam
     * @return
     */
    PageResult<LogOperationInformation> page(LogQueryParam logQueryParam);
}
