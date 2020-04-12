package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.List;

public class Cart implements Packable {

    public long cartId;
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(cartId,productList);
    }

    public Cart(long cartId, List<Product> productList) {
        this.cartId = cartId;
        this.productList = productList;
    }
}
