package com.zp.java8.lambda;

/**
 * LambdaDemo01
 *
 * @author zhengpanone
 * @since 2021-11-26
 */
@FunctionalInterface
interface Interface1{
    int doubleNum(int i);

    default int add(int x, int y){
        return x+y;
    }
}
@FunctionalInterface
interface Interface2{
    int doubleNum(int i);

    default int add(int x, int y){
        return x+y+2;
    }
}

interface Interface3 extends Interface2,Interface1{

    @Override
    default int doubleNum(int i) {
        return 0;
    }

    @Override
    default int add(int x, int y) {
        return x+y;
    }
}

public class LambdaDemo01 {
    public static void main(String[] args) {
        Interface1 i1=(i)->i*2;
        Interface1 i2=i->i*2;
        Interface1 i3 = (int i)->i*2;

        Interface1 i4 = (int i)->{
            return i*2;
        };
        System.out.println( i1.add(3,7));
        System.out.println(i1.doubleNum(20));

    }
}
