package com.obviously20;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

public class UserServiceTest {
    //注解
    //在每个测试方法执行前执行
    @BeforeEach
    public void testBefore(){
        System.out.println("before...");
    }
    //在每个测试方法执行后执行
    @AfterEach
    public void testAfter(){
        System.out.println("after...");
    }
    //在所有测试方法执行前执行
    @BeforeAll //该方法必须被static修饰
    public static void testBeforeAll(){
        System.out.println("before all ...");
    }
    //在所有测试方法执行后执行
    @AfterAll //该方法必须被static修饰
    public static void testAfterAll(){
        System.out.println("after all...");
    }

    //@BeforeEach,@BeforeAll用于初始化资源(准备工作)
    //@AfterEach,@BeforeAll用于释放资源(清理工作)


    //@DisplayName用于指定测试方法的显示名称
    @DisplayName("测试getGender3")
    @ParameterizedTest
    @ValueSource(strings = {"44030419900101001X","44030419900101003X","44030419900101005X"})
    public void testGetGender3(String idcard){
        UserService userService = new UserService();
        String gender = userService.getGender("44030419900101001X");
        //第一种断言方式：比对预期值是否等于结果值
        Assertions.assertEquals("男", gender);
    }






//    单元测试方法发的定义规范
//    注意：
//            - 测试类的命名规范为：XxxxTest
//            - 测试方法的命名规定为：public void testXxxx(){...}

//    JUnit提供了一些辅助方法，用来帮我们确定被测试的方法是否按照预期的效果正常工作，这种方式称为断言
    @DisplayName("测试getAge")
    @Test
    public void testGetAge(){
        UserService userService = new UserService();
        Integer age = userService.getAge("44030419900101001X");
//        System.out.println(age);
        Assertions.assertEquals(36, age);
    }

    @DisplayName("测试getGender")
    @Test
    public void testGetGender(){
        UserService userService = new UserService();
        String gender = userService.getGender("44030419900101001X");
        //第一种断言方式：比对预期值是否等于结果值
//        Assertions.assertEquals("男", gender);

        //第二种有异常抛出
        //    2. 断言异常的类型是否为预期的异常类型
        //第二个参数：被测试的方法（lambda表达式，返回值为void，参数为null）
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getGender("null"));
    }
    /*todo 在JUnit单元测试中，为什么要使用断言?
        单元测试方法运行不报错，不代表业务方法没问题。
        通过断言可以检测方法运行结果是否和预期一致，从而判断业务方法的正确性
        Assertions.assertXxxx(...)*/
}
