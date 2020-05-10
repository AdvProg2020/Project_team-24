package Model.Models;

import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cart implements Packable {

    private static List<Cart> list;

    static {
        DataBase.loadList(Cart.class);
    }

    /*****************************************************fields*******************************************************/

    private long id;
    private List<Long> productSellerIds;
    private List<Product> productList;

    /*****************************************************getters*******************************************************/

    public long getId() {
        return id;
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public List<Long> getProductSellerIds() {
        return Collections.unmodifiableList(productSellerIds);
    }

    public static List<Cart> getList() {
        return list;
    }

    public void addProductToCart(long sellerId, Product product) {
        productSellerIds.add(sellerId);
        productList.add(product);
    }

    public void removeProductFromCart(long sellerId, Product product) {
        productSellerIds.remove(sellerId);
        productList.remove(product);
    }

    /***************************************************otherMethods****************************************************/

    public Product getProductById(long id) {
        return productList.stream()
                .filter(product -> id == product.getProductId())
                .findFirst()
                .orElse(null);
    }

    public double getTotalPrice() {
        return productList.stream().map(Product::getPrice).reduce(0D, Double::sum);
    }

    public static Cart getCartById(long id) {
        return list.stream()
                .filter(cart -> id == cart.getId())
                .findFirst()
                .orElseThrow();
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data pack() {
        return new Data(Cart.class.getName())
                .addField(id)
                .addField(productList.stream()
                .map(Product::getProductId)
                .collect(Collectors.toList()))
                .addField(productSellerIds);
    }

    @Override
    public void dpkg(Data data) throws ProductDoesNotExistException {
        this.id = (long) data.getFields().get(0);
        List<Product> result = new ArrayList<>();
        for (Long aLong : (List<Long>) data.getFields().get(1)) {
            Product productById = Product.getProductById(aLong);
            result.add(productById);
        }
        this.productList = result;
        this.productSellerIds = (List<Long>) data.getFields().get(2);
    }

    /**************************************************constructors*****************************************************/

    public Cart(long id, List<Long> productSellerIds, List<Product> productList) {
        this.id = id;
        this.productSellerIds = productSellerIds;
        this.productList = productList;
    }

    public Cart() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", productSellerIds=" + productSellerIds +
                ", productList=" + productList +
                '}';
    }
}
