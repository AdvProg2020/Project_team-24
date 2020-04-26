package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class Cart implements Packable {

    private static List<Cart> list;

    static {
        DataBase.loadList(Cart.class);
    }

    private long id;
    private List<Product> productList;

    public long getId() {
        return id;
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

    public static List<Cart> getList() {
        return list;
    }

    @Override
    public Data pack() {
        return null;
    }

    @Override
    public void dpkg(Data data) {

    }

    public Cart(long id, List<Product> productList) {
        this.id = id;
        this.productList = productList;
    }
}
