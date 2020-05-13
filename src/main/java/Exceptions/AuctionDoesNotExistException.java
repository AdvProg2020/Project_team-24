package Exceptions;

public class AuctionDoesNotExistException extends Exception{
    public AuctionDoesNotExistException(String message){
        super(message);
    }
}
