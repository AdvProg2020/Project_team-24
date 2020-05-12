package Model.Models;

import Exceptions.CartDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cart implements Packable<Cart> {

    private static List<Cart> list;

    static {
        list = DataBase.loadList("Cart").stream()
                .map(packable -> (Cart) packable)
                .collect(Collectors.toList());
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
        return Collections.unmodifiableList(list);
    }

    /**************************************************addAndRemove*****************************************************/

    public void addProductToCart(long sellerId, Product product) throws Exception {
        productSellerIds.add(sellerId);
        productList.add(product);
        DataBase.save(this, false);
    }

    public void removeProductFromCart(long sellerId, Product product) throws Exception {
        productSellerIds.remove(sellerId);
        productList.remove(product);
        DataBase.save(this, false);
    }

    /***************************************************otherMethods****************************************************/

    public Product getProductById(long id) {
        return productList.stream()
                .filter(product -> id == product.getId())
                .findFirst()
                .orElseThrow(() -> new ProviderNotFoundException("product with this id not exist in this cart."));
    }

    public double getTotalPrice() {
        return productList.stream().map(Product::getPrice).reduce(0D, Double::sum);
    }

    public static Cart getCartById(long id) throws CartDoesNotExistException {
        return list.stream()
                .filter(cart -> id == cart.getId())
                .findFirst()
                .orElseThrow(() -> new CartDoesNotExistException("CartWithThisIdDoesNotExistException"));
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Cart> pack() {
        return new Data<Cart>()
                .addField(id)
                .addField(productList.stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()))
                .addField(productSellerIds)
                .setInstance(new Cart());
    }

    @Override
    public Cart dpkg(Data<Cart> data) throws ProductDoesNotExistException {
        this.id = (long) data.getFields().get(0);
        List<Product> result = new ArrayList<>();
        for (Long aLong : (List<Long>) data.getFields().get(1)) {
            Product productById = Product.getProductById(aLong);
            result.add(productById);
        }
        this.productList = result;
        this.productSellerIds = (List<Long>) data.getFields().get(2);
        return this;
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
