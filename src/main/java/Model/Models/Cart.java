package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;

import java.util.List;

public class Cart implements Packable {

    private static List<Cart> cartList;

    static {
        DataBase.preprocess(Cart.class);
    }

    private long cartId;
    private List<Product> productList;

    public long getCartId() {
        return cartId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product getProductById(long id) {
        return productList.stream()
                .filter(product -> id == product.getProductId())
                .findFirst()
                .orElse(null);
    }

    public double getTotalPrice() {
        return productList.stream()
                .map(Product::getProductInfo)
                .map(ProductInfo::getPrice)
                .reduce(0D, Double::sum);
    }

    public void addToProductList(Product product) {
        productList.add(product);
    }

    public void removeFromProductList(Product product) {
        productList.remove(product);
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
