package com.zp.java8.lambda;

import java.util.function.*;

/**
 * MethodRefrenceDemo
 *
 * @author zhengpanone
 * @since 2021-12-02
 */
class Dog{
    private String name="哮天犬";
    private int food = 10;
    public static void bark(Dog dog){
        System.out.println(dog+"叫了");
    }

    public int eat(int num){
        System.out.println(this.name+"吃了"+num+"斤狗粮");
        this.food -= num;
        return food;
    }
    @Override
    public String toString() {
        return this.name;
    }
}

public class MethodReferenceDemo {
    public static void main(String[] args) {
        // 方法引用
        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("接受数据");
        // 静态方法的引用
        Consumer<Dog> consumer2 = Dog::bark;
        Dog dog = new Dog();
        consumer2.accept(dog);

        // 非静态方法
//        Function<Integer,Integer> function = dog::eat;
        UnaryOperator<Integer>  function = dog::eat;

        System.out.println("还剩下"+function.apply(2)+"斤");
        IntUnaryOperator  function2 = dog::eat;
        System.out.println("还剩下"+function2.applyAsInt(3)+"斤");
    }
}
