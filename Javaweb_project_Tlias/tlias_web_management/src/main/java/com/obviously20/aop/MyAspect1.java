package com.obviously20.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
//@Aspect
/*todo - 在不同切面类中，默认按照切面类的类名字母排序：
            - 目标方法前的通知方法：字母排名靠前的先执行
            - 目标方法后的通知方法：字母排名靠前的后执行
       - 可以在切面类上面加上@Order注解，来控制不同的切面类通知的执行顺序
            - 数值越小，在目标方法前的通知方法执行顺序越靠前，在目标方法后的通知方法后执行顺序越靠后
            - 数值越大，在目标方法前的通知方法执行顺序越靠后，在目标方法后的通知方法后执行顺序越靠前
            */
@Order(1)
public class MyAspect1 {

    //定义切点表达式
    //@Pointcut("xxx"):将公共的切入点表达式抽取出来，需要用到时引用该切入点表达式即可
    @Pointcut("execution(* com.obviously20.service.*.*(..))")
    public void pt(){}


    /*todo 当切入点方法使用private修饰时，仅能在当前切面类中引用该表达式，
       当外部其他切面类中也要引用当前类中的切入点表达式，就需要把private改为public
       具体语法为：
       //引用MyAspect1切面类中的切入点表达式
        @Before("com.obviously20.aop.MyAspect1.pt()")*/


    //前置通知:在目标方法执行前执行
    @Before("pt()")//pt()表示引用了切入点表达式pt()
    public void before(JoinPoint joinPoint){
        log.info("before ...");

    }

    //环绕通知:在目标方法执行前后执行
    @Around("pt()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("around before ...");

        //调用目标对象的原始方法执行
        Object result = proceedingJoinPoint.proceed();
        
        //原始方法如果执行时有异常，环绕通知中的后置代码不会在执行了
        
        log.info("around after ...");

        /*todo 连接点信息 - 对于@Around通知，获取连接点信息只能使用ProceedingJoinPoint类型*/
        //获取目标对象
        Object target = proceedingJoinPoint.getTarget();
        log.info("target: {}", target);
        //获取目标类名
        String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        log.info("className: {}", className);
        //获取目标方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        log.info("methodName: {}", methodName);
        //获取目标方法的参数
        Object[] args = proceedingJoinPoint.getArgs();
        log.info("args: {}", Arrays.toString(args));


        return result;
    }

    //后置通知:在目标方法执行后执行，无论目标方法是否异常执行
    @After("pt()")
    public void after(JoinPoint joinPoint){
        log.info("after ...");

        /*todo 连接点信息 - 对于其他四种通知，获取连接点信息只能使用JoinPoint，它是ProceedingJoinPoint的父类型*/
        //获取目标对象
        Object target = joinPoint.getTarget();
        log.info("target: {}", target);
        //获取目标类名
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.info("className: {}", className);
        //获取目标方法名
        String methodName = joinPoint.getSignature().getName();
        log.info("methodName: {}", methodName);
        //获取目标方法的参数
        Object[] args = joinPoint.getArgs();
        log.info("args: {}", Arrays.toString(args));

    }

    //返回后通知（程序在正常执行的情况下，会执行的后置通知）
    @AfterReturning("pt()")
    public void afterReturning(JoinPoint joinPoint){
        log.info("afterReturning ...");
    }

    //异常通知（程序在出现异常的情况下，执行的后置通知）
    @AfterThrowing("pt()")
    public void afterThrowing(JoinPoint joinPoint){
        log.info("afterThrowing ...");
    }


    /*程序发生异常的情况下：
- @AfterReturning标识的通知方法不会执行，@AfterThrowing标识的通知方法执行了
- @Around环绕通知中原始方法调用时有异常，通知中的环绕后的代码逻辑也不会在执行了 （因为原始方法调用已经出异常了）*/

    /*在使用通知时的注意事项：
- @Around环绕通知需要自己调用 ProceedingJoinPoint.proceed() 来让原始方法执行，其他通知不需要考虑目标方法执行
- @Around环绕通知方法的返回值，必须指定为Object，来接收原始方法的返回值，否则原始方法执行完毕，是获取不到返回值的。*/

//------------------------------------------------------------------------------------------------------------------------
    /*execution主要根据方法的返回值、包名、类名、方法名、方法参数等信息来匹配，语法为：
execution(访问修饰符?  返回值  包名.类名.?方法名(方法参数) throws 异常?)
其中带?的表示可以省略的部分
- 访问修饰符：可省略（比如: public、protected）
- 包名.类名： 可省略
- throws 异常：可省略（注意是方法上声明抛出的异常，不是实际抛出的异常）
注意事项：
- 根据业务需要，可以使用 且（&&）、或（||）、非（!） 来组合比较复杂的切入点表达式。
execution(* com.obviously20.service.DeptService.list(..)) || execution(* com.obviously20.service.DeptService.delete(..))*/

    /*切入点表达式书写建议：
- 所有业务方法名在命名时尽量规范，方便切入点表达式快速匹配。如：findXxx，updateXxx。
- 描述切入点方法通常基于接口描述，而不是直接描述实现类，增强拓展性。
- 在满足业务需要的前提下，尽量缩小切入点的匹配范围。如：包名尽量不使用..，使用 * 匹配单个包。*/

    /*那么如果我们要匹配多个无规则的方法这个时候我们基于execution这种切入点表达式来描述就不是很方便了。而在之前我们是将两个切入点表达式组合在了一起完成的需求，这个是比较繁琐的。
我们可以借助于另一种切入点表达式 @annotation 来描述这一类的切入点，从而来简化切入点表达式的书写。
实现步骤：
1. 编写自定义注解
2. 在业务类要做为连接点的方法上添加自定义注解

自定义注解(在anno包下自定义)：LogOperation
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogOperation{
}

切面类：@Before("@annotation(com.itheima.anno.LogOperation)")
    public void before(){
        log.info("MyAspect6 -> before ...");
    }

业务类：@LogOperation：那个方法要用到就添加这个自定义注解
public void list(){
    log.info("list ...");
}*/

    /*todo "无规则"指的是方法名之间没有共同的模式或规律，难以用 execution 表达式统一描述。
       ​有规则的方法（适合 execution）findAll()
       // 以 find 开头findById()
       // 以 find 开头findByName()
       // 以 find 开头可以用一个表达式匹配：* com.itheima.service.*.find*(..)
       ​无规则的方法（不适合 execution）list() delete()
       这两个方法名没有共同的前缀、后缀或模式，无法用单个 execution 表达式简洁描述*/




}
