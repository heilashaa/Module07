package com.haapp.module07;

public class Test02 {

    @Bracket//неправильные скобки
    public static String check01(){
        return "[[[[[}}}}}";
    }

    //правильные скобки, без аннотации
    public static String check02(){
        return "(sdfds({}))";
    }
}
