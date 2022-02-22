package com.zp.java8.lambda;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * FunctionDemo
 *
 * @author zhengpanone
 * @since 2021-12-02
 */
public class FunctionDemo {
    public static void main(String[] args) {
        //断言函数
        Predicate<Integer> predicate = i->i>0;
        System.out.println(predicate.test(-9));

        IntPredicate intPredicate = i->i>0;
        System.out.println(intPredicate.test(10));
        // 消费函数接口
        Consumer<String> consumer= System.out::println;
        consumer.accept("输入数据");

        IntConsumer intConsumer = i-> System.out.println(i*10);
        intConsumer.accept(100);

    }
}
