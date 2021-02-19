package org.javaboy.vhr.utils;

public class TransUtils {

    public static void trans() {
        /*
        java类型转换分为隐式类型转换和强制类型转换
        隐式转换优先级
        byte,short,char  -> int -> long -> float -> double
         */
        int a = 7;
        byte b = 9;
        int c = a + b;
        System.out.println(c);
    }

}
