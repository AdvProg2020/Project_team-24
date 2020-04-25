package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.util.Date;
import java.util.List;

public class Auction implements Packable, ForPend {

    private static List<Auction> auctionList;

    static {
        DataBase.preprocess(Auction.class);
    }

    private long auctionId;
    private List<Product> productList;
    private PendStatus status;
    private Date start;
    private Date end;
    private Discount discount;

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

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Discount getDiscount() {
        return discount;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Auction(long auctionId, List<Product> productList, PendStatus status, Date start, Date end, Discount discount) {
        this.auctionId = auctionId;
        this.productList = productList;
        this.status = status;
        this.start = start;
        this.end = end;
        this.discount = discount;
    }
}