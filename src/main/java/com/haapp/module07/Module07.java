package com.haapp.module07;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Module07 {

    private static final String OPEN_BRACKETS = "({[<";
    private static final String CLOSE_BRACKETS = ")}]>";

    private static final String SEPARATOR_LINE = "------------------------------------------------------------------";
    private static List<Character> inCome;

    public static void main(String[] args) {

        try (Scanner in = new Scanner(System.in)) {
            separate();
            System.out.println("Please, type a string with some brackets and press ENTER:");
            String inComeLineFromConsole = in.nextLine();
            if (checkBracket(inComeLineFromConsole)) {
                System.out.println("INFO: The brackets are placed correctly in your string: " + inComeLineFromConsole);
            }else{
                throw new BracketException("The brackets are wrong");
            }
            separate();
        } catch (BracketException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void separate() {
        System.out.println(SEPARATOR_LINE);
    }

    private static boolean checkBracket(String inComeLineFromConsole) throws BracketException {

        inCome = inComeLineFromConsole.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        Stack<Character> bracketStack = new Stack<Character>();

        AtomicBoolean result = new AtomicBoolean(true);

        inCome.stream().peek(i -> {
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

        if (!bracketStack.isEmpty()){
            result.set(false);
        }
        return result.get();
    }
}
