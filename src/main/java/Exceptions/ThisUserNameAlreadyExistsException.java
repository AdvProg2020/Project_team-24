package Exceptions;

public class ThisUserNameAlreadyExistsException extends Exception{
    public ThisUserNameAlreadyExistsException(String message){
        super(message);
    }
}
