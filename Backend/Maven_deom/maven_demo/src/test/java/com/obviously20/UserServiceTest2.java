package com.obviously20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserServiceTest2 {
    //测试范围要覆盖所有可能的情况

    private UserService userService;
    //在每个测试方法执行前, 都要初始化资源(准备工作)
    @BeforeEach
    public void beforeEach(){
        userService = new UserService();
    }


    @DisplayName("测试getAge--正常情况")
    @ParameterizedTest
    @ValueSource(strings = {"44030419900101001X","44030419900101003X","44030419900101006X"})
    public void testGetAge(String idcard){
        Integer age = userService.getAge(idcard);
        Assertions.assertEquals(36, age);
    }

    @DisplayName("测试getAge--传null")
    @Test
    public void testGetAgeNull() {
//        Integer age = userService.getAge(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getAge(null));
    }

    @DisplayName("测试getAge--超长18")
    @Test
    public void testGetAgeMore18() {
//        Integer age = userService.getAge("44030419900101001X66");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getAge("44030419900101001X66"));
    }

    @DisplayName("测试getAge--传短18")
    @Test
    public void testGetAgeLess18() {
//        Integer age = userService.getAge("44030419900101001");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getAge("44030419900101001"));
    }

    @DisplayName("测试getAge--传空字符串")
    @Test
    public void testGetAgeEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getAge(""));
    }


    @DisplayName("测试getGender--正常情况(男)")
    @ParameterizedTest
    @ValueSource(strings = {"44030419900101001X","44030419900101003X","44030419900101005X"})
    public void testGetGenderMan(String idcard){
        String gender = userService.getGender(idcard);
        Assertions.assertEquals("男", gender);
    }

    @DisplayName("测试getGender--正常情况(女)")
    @ParameterizedTest
    @ValueSource(strings = {"44030419900101002X","44030419900101004X","44030419900101006X"})
    public void testGetGenderWoman(String idcard){
        String gender = userService.getGender(idcard);
        Assertions.assertEquals("女", gender);
    }

    @DisplayName("测试getGender--传null")
    @Test
    public void testGetGenderNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getGender(null));
    }

    @DisplayName("测试getGender--传短18")
    @Test
    public void testGetGenderLess18() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getGender("44030419900101001"));
    }

    @DisplayName("测试getGender--超长18")
    @Test
    public void testGetGenderMore18() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getGender("44030419900101001X66"));
    }

    @DisplayName("测试getGender--传空字符串")
    @Test
    public void testGetGenderEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getGender(""));
    }








}
