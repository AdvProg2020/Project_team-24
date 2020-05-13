package Exceptions;

public class FieldDoesNotExistException extends Exception {
    public FieldDoesNotExistException(String message){
        super(message);
    }
}
