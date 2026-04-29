package com.obviously20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class EmpServiceTest {
    private EmpService empService;

    @BeforeEach
    public void setUp() {
        empService = new EmpService();
    }

    // --------------测试isBeijing方法----------------
    @DisplayName("测试isBeijing方法--是北京地区")
    @Test
    public void testIsBeijing() {
        boolean isBeijing = empService.isBeijing("11010119900101001X");
        Assertions.assertEquals(true, isBeijing);
    }

    @DisplayName("测试isBeijing方法--不是北京地区")
    @Test
    public void testIsNotBeijing() {
        boolean isnoBeijing = empService.isBeijing("12010119900101001X");
        Assertions.assertEquals(false, isnoBeijing);
    }

    @DisplayName("测试isBeijing方法--多数据测试")
    @ParameterizedTest
    @ValueSource(strings = {"11010119900101001X", "12010119900101001X", "11010119910101001X", "13010119900101001X"})
    public void testIsBeijingMulti(String idcard) {
        boolean bool = empService.isBeijing(idcard);
        Assertions.assertEquals(bool, idcard.startsWith("11"));
    }

    // --------------测试getAge方法----------------
    @DisplayName("测试getAge方法--正常情况")
    @Test
    public void testGetAge() {
        String idcard = "11010119900101001X";
        Integer age = empService.getAge(idcard);
        String birthday = idcard.substring(6, 14);
        LocalDate parse = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyyMMdd"));
        Assertions.assertEquals(Period.between(parse, LocalDate.now()).getYears(), age);
    }

    @DisplayName("测试getAge方法--多数据测试")
    @ParameterizedTest
    @ValueSource(strings = {"11010119900101002X", "12010119900101001X", "11010119910101001X", "13010120060101001X", "13010120260101002X"})
    public void testGetAgeMulti(String idcard) {
        Integer age = empService.getAge(idcard);
        String birthday = idcard.substring(6, 14);
        LocalDate parse = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyyMMdd"));
        Assertions.assertEquals(Period.between(parse, LocalDate.now()).getYears(), age);
    }


    // --------------测试getGender方法----------------

    @DisplayName("测试getGender方法--男")
    @Test
    public void testGetGenderMan() {
        String gender = empService.getGender("11010119900101001X");
        Assertions.assertEquals("男", gender);
    }

    @DisplayName("测试getGender方法--女")
    @Test
    public void testGetGenderWoman() {
        String gender = empService.getGender("12010119900101002X");
        Assertions.assertEquals("女", gender);
    }

    @DisplayName("测试getGender方法--多数据测试")
    @ParameterizedTest
    @ValueSource(strings = {"11010119900101001X", "12010119900101002X", "11010119910101002X", "12010120260101001X"})
    public void testGetGenderWomanMulti(String idcard) {
        String gender = empService.getGender(idcard);
        Assertions.assertEquals(gender, Integer.parseInt(idcard.substring(16,17)) % 2 == 1 ? "男" : "女");
    }


    // --------------测试getYear方法----------------
    @DisplayName("测试getYear方法--正常情况")
    @Test
    public void testGetYear() {
        String idcard = "11010119900101001X";
        String year = empService.getYear(idcard);
        Assertions.assertEquals("1990", year);
    }

    @DisplayName("测试getYear方法--多数据测试")
    @ParameterizedTest
    @ValueSource(strings = {"11010119900101001X", "12010119900101002X", "11010119910101002X", "12010120260101001X"})
    public void testGetYearMulti(String idcard) {
        String year = empService.getYear(idcard);
        Assertions.assertEquals(year, idcard.substring(6,10));
    }

    // --------------测试getMonth方法----------------
    @DisplayName("测试getMonth方法--正常情况")
    @Test
    public void testGetMonth() {
        String idcard = "11010119900101001X";
        String month = empService.getMonth(idcard);
        Assertions.assertEquals("01", month);
    }

    @DisplayName("测试getMonth方法--多数据测试")
    @ParameterizedTest
    @ValueSource(strings = {"11010119900201001X", "12010119900101002X", "11010119910801002X", "12010120251001001X"})
    public void testGetMonthMulti(String idcard) {
        String month = empService.getMonth(idcard);
        Assertions.assertEquals(month, idcard.substring(10,12));
    }

}
