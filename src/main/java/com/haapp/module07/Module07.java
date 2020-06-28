package com.haapp.module07;

public class Module07 {

    public static void main(String[] args) {

        BracketChecker bracketChecker = new BracketChecker();

        for (int i = 0; i < 1000; i++){
            Thread thread = new Thread(bracketChecker);
            thread.start();
        }
    }
}


