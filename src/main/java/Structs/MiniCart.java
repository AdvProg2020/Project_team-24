package Structs;

import java.util.List;

public class MiniCart {

    private final String cartId;
    private final String totalPrice;
    private final List<Long> sellersId;
    private final List<Long> productsId;

    public String getId() {
        return cartId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public List<Long> getProductsId() {
        return productsId;
    }

    public List<Long> getSellersId() {
        return sellersId;
    }

    public MiniCart(String cartId, String totalPrice, List<Long> sellersId, List<Long> productsId) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.sellersId = sellersId;
        this.productsId = productsId;
    }
}
