package Controller.Exceptions;

public class NotEnoughCreditException extends Exception {
    public NotEnoughCreditException(String message){
        super(message);
    }
}
