package com.haapp.module07;

import java.lang.reflect.Method;

public class Module07 {

    public static void main(String[] args) {
        annotationHandler(Test01.class);
        annotationHandler(Test02.class);
    }

    public static void annotationHandler(Class<?> clazz){
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Bracket.class)) {
                try {
                    System.out.println("Testing class: " + clazz.getName());
                    System.out.println("Testing method with annotation \"BRACKET\": " + method.getName());
                    String result = method.invoke(null).toString();
                    BracketChecker bracketChecker = new BracketChecker(result);
                    Thread thread = new Thread(bracketChecker);
                    thread.start();
                    thread.join();
                    System.out.println("---------------------------------------------------");
                } catch (Exception e) {
                   e.getMessage();
                }
            }
        }
    }
}


