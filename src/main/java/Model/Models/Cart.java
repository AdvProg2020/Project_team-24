package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Tools.AddingNew;
import Model.Tools.Data;
import Model.Tools.Packable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart implements Packable<Cart> {

    /*****************************************************fields*******************************************************/

    private static List<Cart> list;

    private long id;
    private List<Long> sellersId;
    private List<Long> productsId;

    /*****************************************************getters*******************************************************/

    public long getId() {
        return id;
    }

    public List<Long> getProductList() {
        return Collections.unmodifiableList(productsId);
    }

    public List<Long> getProductSellers() {
        return Collections.unmodifiableList(sellersId);
    }

    @NotNull
    @Contract(pure = true)
    public static List<Cart> getList() {
        return Collections.unmodifiableList(list);
    }

    /*****************************************************setters*******************************************************/

    public static void setList(List<Cart> list) {
        Cart.list = list;
    }

    private void setId(long id) {
        this.id = id;
    }

    /**************************************************addAndRemove*****************************************************/

    public void addProductToCart(long sellerId, long productId) {
        sellersId.add(sellerId);
        productsId.add(productId);
        DataBase.save(this);
    }

    public void removeProductFromCart(long sellerId, long productId) {
        sellersId.remove(sellerId);
        productsId.remove(productId);
        DataBase.save(this);
    }

    public static void addCart(@NotNull Cart cart) {
        cart.setId(AddingNew.getRegisteringId().apply(getList()));
        list.add(cart);
        DataBase.save(cart, true);
    }

    public static void removeCart(Cart cart) {
        list.remove(cart);
        DataBase.remove(cart);
    }

    /***************************************************otherMethods****************************************************/

    public boolean isThereThisProductInCart(long productId) {
        return productsId.stream().anyMatch(id -> productId == id);
    }

    public double getTotalPrice() throws ProductDoesNotExistException, SellerDoesNotSellOfThisProduct {
        double price = 0;
        for (int i = 0; i < productsId.size(); i++) {
            Product product = Product.getProductById(productsId.get(i));
            price += product.getProductOfSellerById(sellersId.get(i)).getPrice();
        }
        return price;
    }

    public double getTotalAuctionDiscount() throws ProductDoesNotExistException, SellerDoesNotSellOfThisProduct {
        double price = 0;
        for (int i = 0; i < productsId.size(); i++) {
            Product product = Product.getProductById(productsId.get(i));
            Auction auction = product.getAuction();
            if (auction != null) {
                price += auction.getAuctionDiscount(product.getProductOfSellerById(sellersId.get(i)).getPrice());
            }
        }
        return price;
    }

    public static Cart getCartById(long id) throws CartDoesNotExistException {
        return list.stream()
                .filter(cart -> id == cart.getId())
                .findFirst()
                .orElseThrow(() -> new CartDoesNotExistException(
                        "Cart with the id:" + id + "does not exist in list of all carts."
                ));
    }

    @NotNull
    public static Cart autoCreateCart() {
        Cart cart = new Cart(AddingNew.getRegisteringId().apply(getList()), new ArrayList<>(), new ArrayList<>());
        Cart.addCart(cart);
        return cart;
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Cart> pack() {
        return new Data<Cart>()
                .addField(id)
                .addField(productsId)
                .addField(sellersId)
                .setInstance(new Cart());
    }

    @Override
    public Cart dpkg(@NotNull Data<Cart> data) {
        this.id = (long) data.getFields().get(0);
        this.productsId = (List<Long>) data.getFields().get(1);
        this.sellersId  = (List<Long>) data.getFields().get(2);
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Cart(long id, List<Long> sellersId, List<Long> productsId) {
        this.id = id;
        this.sellersId = sellersId;
        this.productsId = productsId;
    }

    private Cart() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", sellersId=" + sellersId +
                ", productsId=" + productsId +
                '}';
    }
}
