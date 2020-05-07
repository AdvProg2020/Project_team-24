package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;
import java.util.stream.Collectors;

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
        return new Data(Cart.class.getName())
                .addField(id)
                .addField(productList.stream()
                .map(Product::getProductId)
                .collect(Collectors.toList()));
    }

    @Override
    public void dpkg(Data data) {
        this.id = (long) data.getFields().get(0);
        this.productList = ((List<Long>) data.getFields().get(1))
                .stream().map(Product::getProductById).collect(Collectors.toList());
    }

    public static Cart getCartById(long id) {
        return list.stream()
                .filter(cart -> id == cart.getId())
                .findFirst()
                .orElseThrow();
    }

    public Cart(long id, List<Product> productList) {
        this.id = id;
        this.productList = productList;
    }

    public Cart() {}
    ////yac

}
