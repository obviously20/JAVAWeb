package com.obviously20.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
@Order(2)
public class RecordTimeAspect {

    @Around("execution(* com.obviously20.service.impl.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        //1.记录开始时间
        long startTime = System.currentTimeMillis();

        //2.执行方法
        Object result = pjp.proceed();

        //3.记录结束时间
        long endTime = System.currentTimeMillis();
        log.info("方法:{} 的执行时间: {}ms", pjp.getSignature().getName(), endTime - startTime);

        //4.返回结果
        return result;

    }

    /*AOP的功能远不止于此，常见的应用场景如下：
    - 记录系统的操作日志
    - 权限控制
    - 事务管理：我们前面所讲解的Spring事务管理，底层其实也是通过AOP来实现的，只要添加@Transactional注解之后，AOP程序自动会在原始方法运行前先来开启事务，在原始方法运行完毕之后提交或回滚事务
     这些都是AOP应用的典型场景。*/

    /*AOP面向切面编程的一些优势：
    - 代码无侵入：没有修改原始的业务方法，就已经对原始的业务方法进行了功能的增强或者是功能的改变
    - 减少了重复代码
    - 提高开发效率
    - 维护方便*/

    /*todo SpringAOP 旨在管理bean对象的过程中，主要通过底层的动态代理机制，对特定的方法进行编程 。*/

}
