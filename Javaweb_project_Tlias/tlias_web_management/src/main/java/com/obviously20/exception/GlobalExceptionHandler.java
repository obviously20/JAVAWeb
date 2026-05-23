package com.obviously20.exception;

import com.obviously20.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
//todo 全局异常处理器（类）：
// 1、在 Spring Boot 中，@RestControllerAdvice（+类上）+@ExceptionHandler注解（+方法上）提供了一个全局异常处理机制，它能拦截并处理所有 Controller 层、Service 层、Mapper 层抛出的异常
// 2、会按照继承关系自下而上匹配，即如果多个处理器都能匹配，会选择“最精确”的那个异常处理方法
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result ex(Exception e){//方法形参中指定能够处理的异常类型
        //提示异常信息并记录日志
        log.error("程序出错了：",e);
        //捕获到异常之后，响应一个标准的Result
        return Result.error("对不起,操作失败,请联系管理员");
    }


    //处理重复键异常(表中字段标有unique约束)
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("重复键异常：",e);
        //获取异常信息
        String message = e.getMessage();
        //从异常信息中提取重复的键值
        int index = message.indexOf("Duplicate entry");
        String errMsg = message.substring(index);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2]+"已存在");
    }

    //处理删除班级异常（班级中还有学生，不能删除）
    @ExceptionHandler
    public Result handleBusinessException(BusinessException e){
        log.error("业务异常：",e);
        return Result.error(e.getMessage());
    }























}
