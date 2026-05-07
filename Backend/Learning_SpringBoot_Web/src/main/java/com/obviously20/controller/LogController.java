package com.obviously20.controller;

import cn.hutool.core.io.IoUtil;
import com.obviously20.pojo.Log;
import com.obviously20.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.obviously20.service.LogService;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/logs")
    private Result list(){

        List<Log> logList = logService.handleLogData();

        //3. 响应结果
        return Result.success(logList);
    }

}
