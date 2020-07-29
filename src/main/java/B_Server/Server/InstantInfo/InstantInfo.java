package B_Server.Server.InstantInfo;

import B_Server.Model.Models.*;

import java.time.LocalTime;

public class InstantInfo {

    private String my_Token;
    private Account account;
    private Product product;
    private Auction auction;
    private DiscountCode code;
    private Category category;
    private LocalTime tmo;
    private Offer offer;
    private int numOfError;

    public void resetErrors() {
        numOfError = 0;
    }

    public void error() {
        numOfError++;
    }

    public int getNumOfError() {
        return numOfError;
    }

    public String getMy_Token() {
        return my_Token;
    }

    public void setMy_Token(String my_Token) {
        this.my_Token = my_Token;
    }

    public LocalTime getTmo() {
        return tmo;
    }

    public void setTmo(LocalTime tmo) {
        this.tmo = tmo;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public DiscountCode getCode() {
        return code;
    }

    public void setCode(DiscountCode code) {
        this.code = code;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public InstantInfo(String my_Token) {
        this.my_Token = my_Token;
    }
}
