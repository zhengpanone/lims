package com.zp.java8.lambda;

import cn.hutool.core.io.LineHandler;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * MoneyDemo
 *
 * @author zhengpanone
 * @since 2021-12-02
 */
/*interface IMoneyFormat{
    String format(int i);
}*/
class MyMoney{
    private final int money;
    public MyMoney(int money){
        this.money=money;
    }
    /*public void printMoney(IMoneyFormat moneyFormat){
        System.out.println("存款"+moneyFormat.format(this.money));
    }*/
    public void printMoney(Function<Integer,String> moneyFormat){
        System.out.println("存款"+moneyFormat.apply(this.money));
    }
}
public class MoneyDemo {
    public static void main(String[] args) {
        MyMoney myMoney = new MyMoney(9999);
//        myMoney.printMoney(i->new DecimalFormat("#,###").format(i));
        Function<Integer,String>  moneyFormat = i -> new DecimalFormat("#,###").format(i);
        myMoney.printMoney(moneyFormat.andThen(s->"人民币"+s));
    }
}
