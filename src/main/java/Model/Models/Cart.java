package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Tools.AddingNew;
import Model.Tools.Data;
import Model.Tools.Packable;

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
    private List<Long> sellersId;
    private List<Product> productList;

    /*****************************************************getters*******************************************************/

    public long getId() {
        return id;
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public List<Long> getProductSellers() {
        return Collections.unmodifiableList(sellersId);
    }

    public static List<Cart> getList() {
        return Collections.unmodifiableList(list);
    }

    /**************************************************addAndRemove*****************************************************/

    public void addProductToCart(long sellerId, Product product) throws CanNotSaveToDataBaseException {
        sellersId.add(sellerId);
        productList.add(product);
        DataBase.save(this);
    }

    public void removeProductFromCart(long sellerId, Product product) throws CanNotSaveToDataBaseException {
        sellersId.remove(sellerId);
        productList.remove(product);
        DataBase.save(this);
    }

    /***************************************************otherMethods****************************************************/

    public Product getProductById(long id) throws ProductDoesNotExistException {
        return productList.stream()
                .filter(product -> id == product.getId())
                .findFirst()
                .orElseThrow(() -> new ProductDoesNotExistException("product with this id not exist in this cart."));
    }

    public double getTotalPrice() {
        double price = 0;
        for (int i = 0; i < productList.size(); i++) {
            price += productList.get(i).getPrice(sellersId.get(i));
        }
        return price;
    }

    public double getTotalAuctionDiscount() {
        double price = 0;
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            Auction auction = product.getAuction();
            if (auction != null) {
                price += auction.getAuctionDiscount(product.getPrice(sellersId.get(i)));
            }
        }
        return price;
    }

    public static Cart getCartById(long id) throws CartDoesNotExistException {
        return list.stream()
                .filter(cart -> id == cart.getId())
                .findFirst()
                .orElseThrow(() -> new CartDoesNotExistException("Cart with Id:" + id + "does not exist."));
    }

    public static Cart autoCreateCart() {
        long id = AddingNew.getRegisteringId().apply(getList());
        return new Cart(id, new ArrayList<>(), new ArrayList<>());
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Cart> pack() {
        return new Data<Cart>()
                .addField(id)
                .addField(productList.stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()))
                .addField(sellersId)
                .setInstance(new Cart());
    }

    @Override
    public Cart dpkg(Data<Cart> data) throws ProductDoesNotExistException {
        this.id = (long) data.getFields().get(0);
        List<Product> ProductResult = new ArrayList<>();
        for (Long aLong : (List<Long>) data.getFields().get(1)) {
            Product productById = Product.getProductById(aLong);
            ProductResult.add(productById);
        }
        this.productList = ProductResult;
        this.sellersId = (List<Long>) data.getFields().get(2);
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Cart(long id, List<Long> sellersId, List<Product> productList) {
        this.id = id;
        this.sellersId = sellersId;
        this.productList = productList;
    }

    private Cart() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", productSellerIds=" + sellersId +
                ", productList=" + productList +
                '}';
    }
}
