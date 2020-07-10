package Exceptions;

public class CommentDoesNotExistException extends Exception{
    public CommentDoesNotExistException(String message){
        super(message);
    }
}
