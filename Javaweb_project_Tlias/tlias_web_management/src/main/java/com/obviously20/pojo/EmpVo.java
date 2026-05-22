package com.obviously20.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//todo
//  PO = 数据库的映射
//  VO = 给前端看的 “精简版对象”
//  为什么要用 VO
//  按需返回：不让前端拿到多余字段（如 phone、内部字段）。
//  隐藏信息：可以不返回 password 等敏感信息。
//  解耦：数据库表改了，VO 可以不改，前端不受影响。
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpVo {
    private Integer id; //ID,主键
    private String username; //用户名
    private String password; //密码
    private String name; //姓名
    private Integer gender; //性别, 1:男, 2:女
//    private String phone; //手机号
    private Integer job; //职位, 1:班主任,2:讲师,3:学工主管,4:教研主管,5:咨询师
    private Integer salary; //薪资
    private String image; //头像
    private LocalDate entryDate; //入职日期
    private Integer deptId; //关联的部门ID
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

    //关联的部门名称
//    private String deptName;
    //关联工作经历列表
//    private List<EmpExpr> exprList;
}
