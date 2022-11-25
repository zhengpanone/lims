package com.zp.util;

import org.junit.jupiter.api.Test;

import java.awt.*;

/**
 * Captch
 *
 * @author zhengpanone
 * @since 2022-11-24
 */
public class CaptchaVerifyUtilTest {
    @Test
    public void test1(){
        Object[] image = CaptchaVerifyUtil.newBuilder().build().createImage();
        System.out.println(image[0]);

    }

    @Test
    public void test2(){
        Object[] image = CaptchaVerifyUtil.newBuilder()
                .setWidth(120)   //设置图片的宽度
                .setHeight(35)   //设置图片的高度
                .setSize(6)      //设置字符的个数
                .setLines(10)    //设置干扰线的条数
                .setFontSize(25) //设置字体的大小
                .setTilt(true)   //设置是否需要倾斜
                .setBackgroundColor(Color.WHITE) //设置验证码的背景颜色
                .build()         //构建VerifyUtil项目
                .createImage();  //生成图片
        System.out.println(image[0]);

    }


}
