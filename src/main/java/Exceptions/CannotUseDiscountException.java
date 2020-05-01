package Exceptions;

public class CannotUseDiscountException extends Exception{
    public CannotUseDiscountException(String message){
        super(message);
    }
}
