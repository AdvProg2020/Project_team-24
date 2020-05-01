package Exceptions;

import org.apache.maven.exception.ExceptionSummary;

public class IllegalAccessException extends Exception {
    public IllegalAccessException(String message){
        super(message);
    }
}
