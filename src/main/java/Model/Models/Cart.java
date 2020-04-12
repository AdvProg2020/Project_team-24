package Model.Models;

import java.util.List;

public class Cart {

    public long cartId;
    private List<Product> productList;

    public Cart(long cartId, List<Product> productList) {
        this.cartId = cartId;
        this.productList = productList;
    }
}
