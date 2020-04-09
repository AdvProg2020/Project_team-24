package Modols.Auction;

import Modols.Discount.Discount;
import Modols.Product.Product;

import java.util.Date;
import java.util.List;

public class Auction {

    protected int auctionId;
    protected List<Product> products;
    protected StatusTag statusTag;
    protected Date start;
    protected Date end;
    protected Discount discount;

    //

    public Auction(int auctionId, List<Product> products, StatusTag statusTag, Date start, Date end, Discount discount) {
        this.auctionId = auctionId;
        this.products = products;
        this.statusTag = statusTag;
        this.start = start;
        this.end = end;
        this.discount = discount;
    }

    enum StatusTag {
        Pending,Editing,Confirmed
    }
}