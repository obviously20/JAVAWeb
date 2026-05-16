package com.obviously20;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class LogTest {

    private static final Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testLog(){

        /*todo - 为什么要在程序中记录日志呢？
            - 便于追踪应用程序中的数据信息、程序的执行过程。
            - 便于对应用程序的性能进行优化。
            - 便于应用程序出现问题之后，排查问题，解决问题。
            - 便于监控系统的运行状态。*/

        /*- 之前我们编写程序时，也可以通过 System.out.println(...) 来输出日志，为什么我们还要学习单独的日志技术呢？
            这是因为，如果通过  System.out.println(...)  来记录日志，会存在以下几点问题：
            - 硬编码。所有的记录日志的代码，都是硬编码，没有办法做到灵活控制，要想不输出这个日志了，只能删除掉记录日志的代码。
            - 只能输出日志到控制台。
            - 不便于程序的扩展、维护。
            所以，在现在的项目开发中，我们一般都会使用专业的日志框架，来解决这些问题。*/

        /*todo
           - Logback：基于Log4j升级而来，提供了更多的功能和配置选项，性能由于Log4j。
            - Slf4j：（Simple Logging Facade for Java）简单日志门面，提供了一套日志操作的标准接口及抽象类，允许应用程序使用不同的底层日志框架。*/

        /*todo
           1). 准备工作：引入logback的依赖（springboot中无需引入，在springboot中已经传递了此依赖）
           <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-classic</artifactId>
                <version>1.4.11</version>
           </dependency>
           2). 引入配置文件 logback.xml  （资料中已经提供，拷贝进来，放在 src/main/resources 目录下； 或者直接AI生成）
           3). 记录日志：定义日志记录对象Logger，记录日志
            //定义日志记录对象
            private static final Logger log = LoggerFactory.getLogger(所在类的名称.class);
*/

//        System.out.println(LocalDateTime.now() + " : 开始计算...");
        logger.debug("开始计算...");

        int sum = 0;
        int[] nums = {1, 5, 3, 2, 1, 4, 5, 4, 6, 7, 4, 34, 2, 23};
        for (int num : nums) {
            sum += num;
        }

        logger.info("计算结果为: {}",sum);
        logger.debug("结束计算...");
        logger.warn("这是一个警告日志");
        logger.error("这是一个错误日志");
        logger.trace("这是一个跟踪日志");
//        System.out.println("计算结果为: "+sum);
//        System.out.println(LocalDateTime.now() + "结束计算...");
    }

}
