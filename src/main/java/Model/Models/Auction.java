package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Auction implements Packable {

    public enum StatusTag {
        Pending, Editing, Confirmed
    }

    public long auctionId;
    private List<Product> productList;
    private StatusTag statusTag;
    private Date start;
    private Date end;
    private Discount discount;

    public List<Product> getProductList() {
        return productList;
    }

    public StatusTag getStatusTag() {
        return statusTag;
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
    public List<Object> getParametersForPack() {
        return Arrays.asList(auctionId, statusTag, productList, start, end, discount);
    }

    public Auction(long auctionId, List<Product> productList, StatusTag statusTag, Date start, Date end, Discount discount) {
        this.auctionId = auctionId;
        this.productList = productList;
        this.statusTag = statusTag;
        this.start = start;
        this.end = end;
        this.discount = discount;
    }
}