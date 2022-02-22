package com.zp.enums;


import com.zp.util.enums.EnumUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * EnumTest
 * https://www.bilibili.com/video/BV1gS4y1V7M6?spm_id_from=333.1007.top_right_bar_window_history.content.click
 *
 * @author zhengpanone
 * @since 2022-02-22
 */
public class EnumTest {
    @Test
    public void isExist() {
        Assert.assertTrue(EnumUtils.isExist(TestStrEnum.values(), "01"));
        Assert.assertFalse(EnumUtils.isExist(TestStrEnum.values(), "03"));
        Assert.assertTrue(EnumUtils.isExist(TestIntegerEnum.values(), 1));
        Assert.assertFalse(EnumUtils.isExist(TestIntegerEnum.values(), 3));
    }

    @Test
    public void getNameByValue() {
        String name1 = EnumUtils.getNameByValue(TestStrEnum.values(), "01");
        String name2 = EnumUtils.getNameByValue(TestIntegerEnum.values(), 1);
        System.out.println(name1);
        System.out.println(name2);
    }

    @Test
    public void getValueByName() {
        Integer value1 = EnumUtils.getValueByName(TestIntegerEnum.values(), "Integer测试01");
        String value2 = EnumUtils.getValueByName(TestStrEnum.values(), "String类型测试01");
        System.out.println(value1);
        System.out.println(value2);
    }

    @Test
    public void getEnumByValue() {
        TestStrEnum enum1 = EnumUtils.getEnumByValue(TestStrEnum.class, "01");
        TestIntegerEnum enum2 = EnumUtils.getEnumByValue(TestIntegerEnum.values(), 1);
        System.out.println(enum1);
        System.out.println(enum2);
    }
}
