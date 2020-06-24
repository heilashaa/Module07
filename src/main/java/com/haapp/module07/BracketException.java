package com.haapp.module07;

public class BracketException extends Exception {

    public BracketException(String message){
        super("ERROR: " + message);
    }
}
