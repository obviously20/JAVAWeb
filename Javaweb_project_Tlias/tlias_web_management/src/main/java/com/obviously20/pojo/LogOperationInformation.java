package com.obviously20.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogOperationInformation {
    private Integer id;//主键ID
    private Integer operateEmpId;//操作人ID
    private String operateTime;//操作时间
    private String className;//类名
    private String methodName;//方法名
    private String methodParams;//请求参数
    private String returnValue;//返回值
    private Long costTime;//操作耗时（MS）
    private String operateEmpName;//操作人
}
