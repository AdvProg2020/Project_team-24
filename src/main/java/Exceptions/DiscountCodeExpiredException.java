package Exceptions;

public class DiscountCodeExpiredException extends Exception {
    public DiscountCodeExpiredException(String message){
        super(message);
    }
}
