package Exceptions;

import jdk.jshell.spi.ExecutionControlProvider;

public class AddresInvalidException extends Exception {
    public AddresInvalidException(String message){
        super(message);
    }
}
