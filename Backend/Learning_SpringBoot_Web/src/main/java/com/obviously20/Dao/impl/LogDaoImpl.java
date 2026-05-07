package com.obviously20.Dao.impl;

import cn.hutool.core.io.IoUtil;
import com.obviously20.Dao.LogDao;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;


@Repository
public class LogDaoImpl implements LogDao {
    @Override
    public ArrayList<String> getAllLog() {
        //1. 加载log.txt文件数据
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("log.txt");
        ArrayList<String> lines = IoUtil.readUtf8Lines(inputStream, new ArrayList<String>());
        return lines;
    }
}
