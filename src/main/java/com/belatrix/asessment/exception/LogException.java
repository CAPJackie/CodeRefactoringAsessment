package com.belatrix.asessment.exception;

public class LogException extends Exception {
    public static final String INVALID_CONFIG = "Invalid configuration";

    public LogException (String message){
        super(message);
    }
}
