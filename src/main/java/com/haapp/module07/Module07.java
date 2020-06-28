package com.haapp.module07;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Module07 {

    private static final String OPEN_BRACKETS = "({[<";
    private static final String CLOSE_BRACKETS = ")}]>";
    private static String inComeLine;
    private static int counter;


    public static void main(String[] args) {

        Runnable check = Module07::run;

        for (int i = 0; i < 1000; i++){
            Thread thread = new Thread(check);
            thread.start();
            counter++;
        }
    }

    private static void run() {
        try {
            fileReader("src/main/resources/income.txt");
            if (!checkBracket(inComeLine)) {
                throw new BracketException("The brackets are wrong in your string: " + inComeLine);
            }
            System.out.println("INFO: The brackets are placed correctly in your string: " + inComeLine);
        } catch (BracketException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static boolean checkBracket(String inComeLine) {
        List<Character> listInComeChars = inComeLine.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        Stack<Character> bracketStack = new Stack<Character>();
        AtomicBoolean result = new AtomicBoolean(true);
        listInComeChars.stream().peek(i -> {
                    if (OPEN_BRACKETS.contains(i.toString())) {
                        bracketStack.push(i);
                    }

                    if (CLOSE_BRACKETS.contains(i.toString())) {
                        if (bracketStack.isEmpty() || (i - bracketStack.pop()) > 2) {
                            result.set(false);
                        }
                    }
                }
        ).collect(Collectors.toList());
        if (!bracketStack.isEmpty()) {
            result.set(false);
        }
        return result.get();
    }

    private static void fileReader(String filePath) {
        try (FileReader fr = new FileReader(filePath); BufferedReader reader = new BufferedReader(fr)) {
                inComeLine = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


