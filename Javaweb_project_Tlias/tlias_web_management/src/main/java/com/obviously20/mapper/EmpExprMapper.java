package com.obviously20.mapper;

import com.obviously20.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    /**
     * 批量插入员工的工作经历信息
     * @param exprList 员工的工作经历信息列表
     */
    void insertBatch(List<EmpExpr> exprList);
}
