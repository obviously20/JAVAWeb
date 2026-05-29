package com.obviously20.mapper;

import com.obviously20.pojo.LogOperationInformation;
import com.obviously20.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);

    /**
     * 查询所有日志
     * */
    @Select("select ol.*,e.name as operateEmpName from operate_log ol join emp e on ol.operate_emp_id=e.id")
    List<LogOperationInformation> selectList();
}
