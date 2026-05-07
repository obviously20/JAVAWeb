package com.obviously20.service.impl;

import com.obviously20.Dao.LogDao;
import com.obviously20.pojo.Log;
import com.obviously20.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public List<Log> handleLogData() {

        ArrayList<String> lines = logDao.getAllLog();

        //2. 对原始数据进行处理 , 组装日志数据
        List<Log> logList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            return new Log(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
        }).collect(Collectors.toList());
        return logList;
    }
}
