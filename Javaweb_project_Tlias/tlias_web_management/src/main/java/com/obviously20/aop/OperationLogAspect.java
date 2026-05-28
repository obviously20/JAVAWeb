package com.obviously20.aop;


import com.obviously20.mapper.OperateLogMapper;
import com.obviously20.pojo.OperateLog;
import com.obviously20.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    //环绕通知
    @Around("@annotation(com.obviously20.anno.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录开始时间
        long startTime = System.currentTimeMillis();
        //执行目标方法
        Object result = joinPoint.proceed();
        //记录结束时间
        long endTime = System.currentTimeMillis();
        //计算耗时
        long costTime = endTime - startTime;

        //创建日志对象
        OperateLog olog = new OperateLog();
        olog.setOperateEmpId(getEmpId());//获取当前登录用户ID，这里简单设为1，实际应用中需要从上下文获取用户ID
        olog.setOperateTime(LocalDateTime.now());//设置操作时间
        olog.setClassName(joinPoint.getTarget().getClass().getName());//执行目标方法的全类名
        olog.setMethodName(joinPoint.getSignature().getName());//执行目标方法的方法名
        olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));//执行目标方法的参数
        olog.setReturnValue(result.toString());//执行目标方法的返回值
        olog.setCostTime(costTime);//设置操作耗时

        //插入日志数据
        log.info("插入日志数据: {}", olog);
        operateLogMapper.insert(olog);
        return result;
    }

    //获取当前登录用户ID
    private Integer getEmpId() {
        return CurrentHolder.getCurrentId();
    }

}
