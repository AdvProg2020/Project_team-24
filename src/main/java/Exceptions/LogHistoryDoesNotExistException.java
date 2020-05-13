package Exceptions;

public class LogHistoryDoesNotExistException extends Exception{
    public LogHistoryDoesNotExistException(String message){
        super(message);
    }
}
