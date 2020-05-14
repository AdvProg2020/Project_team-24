package Exceptions;

public class ProductIsOutOfStockException extends Exception{
    public ProductIsOutOfStockException(String message){
        super(message);
    }
}
