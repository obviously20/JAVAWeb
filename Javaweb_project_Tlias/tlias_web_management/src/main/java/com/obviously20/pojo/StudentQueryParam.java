package com.obviously20.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentQueryParam {
    private String name;
    private Integer degree;//最高学历：1:初中, 2:高中, 3:大专, 4:本科, 5:硕士, 6:博士'
    private Integer clazzId;//班级ID
    private Integer page = 1;//当前页
    private Integer pageSize = 10;//每页数量
}
