package Exceptions;

public class SellerDoesNotSellOfThisProduct extends Exception{
    public SellerDoesNotSellOfThisProduct(String message){
        super(message);
    }
}
