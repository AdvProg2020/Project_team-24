package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Models.Accounts.Seller;
import Model.Tools.AddingNew;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.io.IOException;

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
    private List<Seller> productSellers;
    private List<Product> productList;

    /*****************************************************getters*******************************************************/

    public long getId() {
        return id;
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public List<Seller> getProductSellers() {
        return Collections.unmodifiableList(productSellers);
    }

    public static List<Cart> getList() {
        return Collections.unmodifiableList(list);
    }

    /**************************************************addAndRemove*****************************************************/

    public void addProductToCart(Seller seller, Product product) throws CanNotSaveToDataBaseException {
        productSellers.add(seller);
        productList.add(product);
        DataBase.save(this, false);
    }

    public void removeProductFromCart(Seller seller, Product product) throws CanNotSaveToDataBaseException {
        productSellers.remove(seller);
        productList.remove(product);
        DataBase.save(this, false);
    }

    /***************************************************otherMethods****************************************************/

    public Product getProductById(long id) throws ProductDoesNotExistException {
        return productList.stream()
                .filter(product -> id == product.getId())
                .findFirst()
                .orElseThrow(() -> new ProductDoesNotExistException("product with this id not exist in this cart."));
    }

    public double getTotalPrice() throws FieldDoesNotExistException {
        double acc = 0D;
        for (Product product : productList) {
            double price = product.getPrice();
            acc = acc + price;
        }
        return acc;
    }

    public double getTotalAuctionDiscount() throws FieldDoesNotExistException {
        double sum = 0;
        for (Product product : productList) {

            Auction auction = product.getAuction();

            if (auction != null) {
                sum += auction.getAuctionDiscount(product.getPrice());
            }
        }
        return sum;
    }

    public static Cart getCartById(long id) throws CartDoesNotExistException {
        return list.stream()
                .filter(cart -> id == cart.getId())
                .findFirst()
                .orElseThrow(() -> new CartDoesNotExistException("CartWithThisIdDoesNotExistException"));
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
                .addField(productSellers.stream()
                        .map(Account::getId)
                        .collect(Collectors.toList()))
                .setInstance(new Cart());
    }

    @Override
    public Cart dpkg(Data<Cart> data) throws ProductDoesNotExistException, AccountDoesNotExistException {
        this.id = (long) data.getFields().get(0);
        List<Product> ProductResult = new ArrayList<>();
        for (Long aLong : (List<Long>) data.getFields().get(1)) {
            Product productById = Product.getProductById(aLong);
            ProductResult.add(productById);
        }
        this.productList = ProductResult;
        List<Seller> SellerResult = new ArrayList<>();
        for (Long aLong : (List<Long>) data.getFields().get(2)) {
            Seller sellerById = (Seller)Account.getAccountById(aLong);
            SellerResult.add(sellerById);
        }
        this.productSellers = SellerResult;
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Cart(long id, List<Seller> productSellers, List<Product> productList) {
        this.id = id;
        this.productSellers = productSellers;
        this.productList = productList;
    }

    private Cart() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", productSellerIds=" + productSellers +
                ", productList=" + productList +
                '}';
    }
}
