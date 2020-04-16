package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.io.File;
import java.util.List;

public class Cart implements Packable {

    private static File cartSource;
    private static List<Cart> cartList;

    static {

    }

    private long cartId;
    private List<Product> productList;

    public long getCartId() {
        return cartId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public static List<Cart> getCartList() {
        return cartList;
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
