package Exceptions;

public class CannotShopAsAGuestException extends Exception{
    public CannotShopAsAGuestException(String message){
        super(message);
    }
}
