package Model.Models;

import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Auction implements Packable <Auction>, ForPend {

    private static List<Auction> list;

    static {
        DataBase.loadList(Auction.class);
    }

    /*****************************************************fields*******************************************************/

    private long auctionId;
    private List<Product> productList;
    private String stateForPend;
    private Date start;
    private Date end;
    private Discount discount;

    /*****************************************************getters*******************************************************/

    public static List<Auction> getList() {
        return Collections.unmodifiableList(list);
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public long getId() {
        return auctionId;
    }

    public String getStatus() {
        return stateForPend;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Discount getDiscount() {
        return discount;
    }

    /*****************************************************setters*******************************************************/

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setStateForPend(String stateForPend) {
        this.stateForPend = stateForPend;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**************************************************addAndRemove*****************************************************/

    public void addAuction(Auction auction) throws Exception {
        list.add(auction);
        DataBase.save(auction, true);
    }

    public void removeAuction(Auction auction) throws Exception {
        list.remove(auction);
        DataBase.remove(auction);
    }

    public void addProductToAuction(Product product) throws Exception {
        productList.add(product);
        DataBase.save(this, false);
    }

    public void removeProductFromAuction(Product product) throws Exception {
        productList.remove(product);
        DataBase.save(this, false);
    }

    /***************************************************otherMethods****************************************************/

    public static Auction getAuctionById(long id) {
        return list.stream()
                .filter(auction -> id == auction.getId())
                .findFirst()
                .orElseThrow(); // need auction does not exist exception.
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data pack() {
        return new Data(Auction.class.getName())
                .addField(auctionId)
                .addField(productList.stream()
                        .map(Product::getId).collect(Collectors.toList()))
                .addField(stateForPend)
                .addField(start)
                .addField(end)
                .addField(discount);
    }

    @Override
    public Auction dpkg(Data data) throws ProductDoesNotExistException {
        this.auctionId = (long) data.getFields().get(0);
        List<Product> result = new ArrayList<>();
        for (Long aLong : ((List<Long>) data.getFields().get(1))) {
            Product productById = Product.getProductById(aLong);
            result.add(productById);
        }
        this.productList = result;
        this.stateForPend = (String) data.getFields().get(2);
        this.start = (Date) data.getFields().get(3);
        this.end = (Date) data.getFields().get(4);
        this.discount = (Discount) data.getFields().get(5);
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Auction(long auctionId, List<Product> productList, String status, Date start, Date end, Discount discount) {
        this.auctionId = auctionId;
        this.productList = productList;
        this.stateForPend = status;
        this.start = start;
        this.end = end;
        this.discount = discount;
    }

    public Auction() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Auction{" +
                "auctionId=" + auctionId +
                ", productList=" + productList +
                ", status=" + stateForPend +
                ", start=" + start +
                ", end=" + end +
                ", discount=" + discount +
                '}';
    }
}