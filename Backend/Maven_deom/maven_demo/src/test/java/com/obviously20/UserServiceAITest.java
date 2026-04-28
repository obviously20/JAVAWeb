package com.obviously20;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * UserService 的单元测试类
 */
@DisplayName("UserService 单元测试")
public class UserServiceAITest {
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    // ==================== getAge 方法测试 ====================

//    @DisplayName("测试 getAge - 正常情况（1990年出生）")
//    @ParameterizedTest
//    @ValueSource(strings = {"44030419900101001X", "44030419900131002X", "44030419900215003X"})
//    public void testGetAge_NormalCase(String idCard) {
//        Integer age = userService.getAge(idCard);
//        // 2026 - 1990 = 36 岁（假设今天是2026年）
////        Assertions.assertEquals(36, age);
////        这个断言假设当前年份是2026年，但如果测试在其他年份运行，或者用户的生日还没到，测试就会失败。
//        // 动态计算预期年龄，避免硬编码，但还是只能测试到当前系统时间的前面
//        int expectedAge = java.time.LocalDate.now().getYear() - Integer.parseInt(idCard.substring(6, 10));
//        Assertions.assertEquals(expectedAge, age);
//    }

    @DisplayName("测试 getAge - 正常情况（1990年出生）")
    @ParameterizedTest
    @ValueSource(strings = {"44030419900101001X", "44030419901231002X", "44030419900615003X"})
    public void testGetAge_NormalCase(String idCard) {
        Integer age = userService.getAge(idCard);
        // 使用与UserService相同的逻辑计算预期年龄
        String birthday = idCard.substring(6, 14);
        java.time.LocalDate birthDate = java.time.LocalDate.parse(birthday, java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        int expectedAge = java.time.Period.between(birthDate, java.time.LocalDate.now()).getYears();
        Assertions.assertEquals(expectedAge, age);
    }

    @DisplayName("测试 getAge - 边界情况：刚出生")
    @Test
    public void testGetAge_Newborn() {
        // 使用当前日期构造一个刚出生的身份证号（简化测试）
        String newbornIdCard = "44030420260428001X";
        Integer age = userService.getAge(newbornIdCard);
        Assertions.assertEquals(0, age);
    }

    @DisplayName("测试 getAge - 边界情况：传入 null")
    @Test
    public void testGetAge_NullIdCard() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.getAge(null),
                "预期抛出 IllegalArgumentException");
    }

    @DisplayName("测试 getAge - 边界情况：长度不足18位")
    @Test
    public void testGetAge_LessThan18Digits() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.getAge("44030419900101"),
                "预期抛出 IllegalArgumentException");
    }

    @DisplayName("测试 getAge - 边界情况：长度超过18位")
    @Test
    public void testGetAge_MoreThan18Digits() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.getAge("44030419900101001X1"),
                "预期抛出 IllegalArgumentException");
    }

    // 新增参数化测试：测试不同年份出生的年龄计算
    @DisplayName("测试getAge--不同出生年份")
    @ParameterizedTest
    @ValueSource(strings = {
            "44030419800101001X",  // 1980年出生，46岁
            "44030419900101001X",  // 1990年出生，36岁
            "44030420000101001X",  // 2000年出生，26岁
            "44030420100101001X",  // 2010年出生，16岁
            "44030420200101001X"   // 2020年出生，6岁
    })
    public void testGetAge_DifferentYears(String idcard) {
        Integer age = userService.getAge(idcard);
        int expectedAge = 2026 - Integer.parseInt(idcard.substring(6, 10));
        Assertions.assertEquals(expectedAge, age);
    }

    // ==================== getGender 方法测试 ====================

    @DisplayName("测试 getGender - 正常情况：男性（第17位为奇数）")
    @ParameterizedTest
    @ValueSource(strings = {"44030419900101001X", "44030419900101003X", "44030419900101005X"})
    public void testGetGender_Male(String idCard) {
        String gender = userService.getGender(idCard);
        Assertions.assertEquals("男", gender);
    }

    @DisplayName("测试 getGender - 正常情况：女性（第17位为偶数）")
    @ParameterizedTest
    @ValueSource(strings = {"44030419900101002X", "44030419900101004X", "44030419900101006X"})
    public void testGetGender_Female(String idCard) {
        String gender = userService.getGender(idCard);
        Assertions.assertEquals("女", gender);
    }

    @DisplayName("测试 getGender - 边界情况：传入 null")
    @Test
    public void testGetGender_NullIdCard() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.getGender(null),
                "预期抛出 IllegalArgumentException");
    }

    @DisplayName("测试 getGender - 边界情况：长度不足18位")
    @Test
    public void testGetGender_LessThan18Digits() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.getGender("44030419900101"),
                "预期抛出 IllegalArgumentException");
    }

    @DisplayName("测试 getGender - 边界情况：长度超过18位")
    @Test
    public void testGetGender_MoreThan18Digits() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.getGender("44030419900101001X1"),
                "预期抛出 IllegalArgumentException");
    }


    @DisplayName("测试getGender--不同性别标识")
    @ParameterizedTest
    @ValueSource(strings = {
            "44030419900101001X",  // 第17位是1，男性
            "44030419900101003X",  // 第17位是3，男性
            "44030419900101005X",  // 第17位是5，男性
            "44030419900101007X",  // 第17位是7，男性
            "44030419900101009X",  // 第17位是9，男性
            "44030419900101000X",  // 第17位是0，女性
            "44030419900101002X",  // 第17位是2，女性
            "44030419900101004X",  // 第17位是4，女性
            "44030419900101006X",  // 第17位是6，女性
            "44030419900101008X"   // 第17位是8，女性
    })
    public void testGetGender_DifferentDigits(String idcard) {
        String gender = userService.getGender(idcard);
        int genderDigit = Integer.parseInt(idcard.substring(16, 17));
        String expectedGender = genderDigit % 2 == 1 ? "男" : "女";
        Assertions.assertEquals(expectedGender, gender);
    }


}
