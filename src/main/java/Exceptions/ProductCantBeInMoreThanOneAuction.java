package Exceptions;

public class ProductCantBeInMoreThanOneAuction extends Exception {
    public ProductCantBeInMoreThanOneAuction(String message) {
        super(message);
    }
}
