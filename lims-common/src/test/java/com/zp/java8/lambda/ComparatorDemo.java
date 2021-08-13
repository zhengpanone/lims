package com.zp.java8.lambda;

import java.util.Comparator;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/08 12:03.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
public class ComparatorDemo {
    public static void main(String[] args) {
        getComparator();
    }

    private static Comparator<String> getComparator() {
        new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };
        return null;
    }
}
