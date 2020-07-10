package Exceptions;

public class CartDoesNotExistException extends Exception{
    public CartDoesNotExistException(String message){
        super(message);
    }
}
