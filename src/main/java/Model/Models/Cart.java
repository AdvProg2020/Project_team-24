package Model.Models;

import Exceptions.IdInvalidException;
import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Models.Info.ProductInfo;
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

    private long id;
    private List<Long> productSellerIds;
    private List<Product> productList;

    public long getId() {
        return id;
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public List<Long> getProductSellerIds() {
        return Collections.unmodifiableList(productSellerIds);
    }

    public void addProductToCart(long sellerId, Product product) {
        productSellerIds.add(sellerId);
        productList.add(product);
    }

    public void removeProductFromCart(long sellerId, Product product) throws IdInvalidException {
        long id = productSellerIds.stream()
                .dropWhile(i -> i == sellerId)
                .count();
        if (id == productSellerIds.size() + 1) {

        }
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

    public static List<Cart> getList() {
        return list;
    }

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

    public static Cart getCartById(long id) {
        return list.stream()
                .filter(cart -> id == cart.getId())
                .findFirst()
                .orElseThrow();
    }

    public Cart(long id, List<Long> productSellerIds, List<Product> productList) {
        this.id = id;
        this.productSellerIds = productSellerIds;
        this.productList = productList;
    }

    public Cart() {
    }
}
