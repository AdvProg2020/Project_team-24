package Model.Models;

import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.util.List;

public class Auction implements Packable, ForPend {

    private static List<Auction> auctionList;

    static {

    }

    private long auctionId;
    private List<Product> productList;
    private PendStatus status;
    //    Date start;
//    Date end;
//    Discount discount;
    private List<Field> fieldList;

    public static List<Auction> getAuctionList() {
        return auctionList;
    }

    public long getAuctionId() {
        return auctionId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public PendStatus getStatus() {
        return status;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Auction(long auctionId, List<Product> productList, PendStatus status, List<Field> fieldList) {
        this.auctionId = auctionId;
        this.productList = productList;
        this.status = status;
        this.fieldList = fieldList;
    }
}