package Exceptions;

public class InvalidDiscountCodeException extends Exception {
    public InvalidDiscountCodeException(String message){
        super(message);
    }
}
