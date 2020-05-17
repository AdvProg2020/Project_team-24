package Exceptions;

import Model.Models.Accounts.Seller;

public class SellerDoesNotSellThisProduct extends Exception{
    public SellerDoesNotSellThisProduct(String message){
        super(message);
    }
}
