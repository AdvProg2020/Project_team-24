package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class Cart implements Packable {

    public long cartId;
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Cart(long cartId, List<Product> productList) {
        this.cartId = cartId;
        this.productList = productList;
    }
}
