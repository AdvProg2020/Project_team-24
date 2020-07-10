package Exceptions;

public class InvalidStartAndEndDateForDiscountCodeException extends Exception{
    public InvalidStartAndEndDateForDiscountCodeException(String message){
        super(message);
    }
}
