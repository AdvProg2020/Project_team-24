package Exceptions;

import jdk.jshell.spi.ExecutionControlProvider;

public class InvalidDiscountCodeException extends Exception {
    public InvalidDiscountCodeException(String message){
        super(message);
    }
}
