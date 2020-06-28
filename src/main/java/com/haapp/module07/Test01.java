package com.haapp.module07;

public class Test01 {

    @Bracket//неправильные скобки
    public static String check01(){
        return "999(()";
    }

    @Bracket//правильные скобки
    public static String check02(){
        return "999{(())}fsdf";
    }
}
