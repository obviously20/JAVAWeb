package com.obviously20.aop;

import com.obviously20.mapper.EmpLoginLogMapper;
import com.obviously20.pojo.Emp;
import com.obviously20.pojo.EmpLoginLog;
import com.obviously20.pojo.LoginInfo;
import com.obviously20.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class EmpLoginLogAspect {

    @Autowired
    private EmpLoginLogMapper empLoginLogMapper;


    @Around("execution(* com.obviously20.controller.LoginController.login(..))")
    public Object aroundLogin(ProceedingJoinPoint pjp) throws Throwable {
        //记录开始时间
        long startTime = System.currentTimeMillis();
        //调用目标方法
        Object result = pjp.proceed();
        //记录结束时间
        long endTime = System.currentTimeMillis();
        //计算耗时
        long costTime = endTime - startTime;


        //创建EmpLoginLog对象
        EmpLoginLog empLoginLog = new EmpLoginLog();

        //用户名（登陆时，输入的用户名）--在LoginController的login方法中的执行参数中
        //获取接入点的执行参数:有多个参数，需要遍历数组
        //同样：密码--存入password
        Object[] args = pjp.getArgs();
        if (args.length > 0 && args[0] instanceof com.obviously20.pojo.Emp) {
            com.obviously20.pojo.Emp emp = (com.obviously20.pojo.Emp) args[0];
            empLoginLog.setUsername(emp.getUsername());
            empLoginLog.setPassword(emp.getPassword());
        }
        //操作时间：当前时间
        empLoginLog.setLoginTime(LocalDateTime.now());
        //是否登录成功, 1:成功, 0:失败--在原始方法执行后的返回值中，可以通过Result来获取code从而判断成功还是失败 -- 可以强转
        empLoginLog.setIsSuccess(getLoginStatus(result));
        //成功后, 下发的JWT令牌--jwt在原始方法执行后的返回值中  -- 可以强转
        empLoginLog.setJwt(getJwtToken(result));
        //存入costTime
        empLoginLog.setCostTime(costTime);

        //记录登录日志
        log.info("登录日志:{}", empLoginLog);
        //调用Mapper中的insert方法
        empLoginLogMapper.insert(empLoginLog);
        return result;

    }

    private Short getLoginStatus(Object result) {
        // 直接将返回值转换为Result类型，然后调用getCode方法
        if (result instanceof com.obviously20.pojo.Result) {
            com.obviously20.pojo.Result resultObj = (com.obviously20.pojo.Result) result;
            Integer code = resultObj.getCode();
            // 根据Result类定义，code为1表示成功，0表示失败
            return code != null && code == 1 ? (short) 1 : (short) 0;
        }
        // 如果不是Result类型，默认为失败
        return (short) 0;
    }

    private String getJwtToken(Object result) {
        try {
            if (result instanceof com.obviously20.pojo.Result) {
                com.obviously20.pojo.Result resultObj = (com.obviously20.pojo.Result) result;
                if (resultObj.getData() != null && resultObj.getData() instanceof com.obviously20.pojo.LoginInfo) {
                    com.obviously20.pojo.LoginInfo loginInfo = (com.obviously20.pojo.LoginInfo) resultObj.getData();
                    return loginInfo.getToken();
                }
            }
        } catch (Exception e) {
            log.error("获取JWT令牌失败: {}", e.getMessage());
        }
        return null; // 获取失败时返回null
    }


}
