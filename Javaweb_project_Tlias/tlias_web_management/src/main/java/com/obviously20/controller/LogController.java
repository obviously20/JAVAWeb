package com.obviously20.controller;

import com.obviously20.pojo.LogOperationInformation;
import com.obviously20.pojo.LogQueryParam;
import com.obviously20.pojo.PageResult;
import com.obviously20.pojo.Result;
import com.obviously20.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("page")
    public Result page(LogQueryParam logQueryParam) {
        log.info("logQueryParam:page{},pageSize{}", logQueryParam.getPage(), logQueryParam.getPageSize());
        PageResult<LogOperationInformation> pageResult = logService.page(logQueryParam);
        return Result.success(pageResult);

    }

}
