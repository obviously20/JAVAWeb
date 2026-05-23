package com.obviously20.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//封装职位人数统计结果（jobList 、dataList）
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobOption {

    private List jobList;
    private List dataList;

}
