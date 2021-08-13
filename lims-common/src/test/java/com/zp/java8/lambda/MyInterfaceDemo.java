package com.zp.java8.lambda;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/08 11:38.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
public class MyInterfaceDemo {
    public static void main(String[] args) {
        MyInterface my = () -> System.out.println("I'm FunctionalInterface");
        my.show();
    }
}
