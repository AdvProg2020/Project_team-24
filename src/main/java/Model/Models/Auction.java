package Model.Models;

import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Auction implements Packable, ForPend {

    private static List<Auction> list;

    static {
        DataBase.loadList(Auction.class);
    }

    private long auctionId;
    private List<Product> productList;
    private PendStatus status;
    private Date start;
    private Date end;
    private Discount discount;

    public static List<Auction> getList() {
        return list;
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
    public Data pack() {
        return new Data(Auction.class.getName())
                .addField(auctionId)
                .addField(productList.stream()
                .map(Product::getProductId).collect(Collectors.toList()))
                .addField(status)
                .addField(start)
                .addField(end)
                .addField(discount);
    }

    @Override
    public void dpkg(Data data) throws ProductDoesNotExistException {
        this.auctionId = (long) data.getFields().get(0);
        List<Product> result = new ArrayList<>();
        for (Long aLong : ((List<Long>) data.getFields().get(1))) {
            Product productById = Product.getProductById(aLong);
            result.add(productById);
        }
        this.productList = result;
        this.status = (PendStatus) data.getFields().get(2);
        this.start = (Date) data.getFields().get(3);
        this.end = (Date) data.getFields().get(4);
        this.discount = (Discount) data.getFields().get(5);
    }

    public static Auction getAuctionById(long id) {
        return list.stream()
                .filter(auction -> id == auction.getAuctionId())
                .findFirst()
                .orElseThrow();
    }

    public Auction(long auctionId, List<Product> productList, PendStatus status, Date start, Date end, Discount discount) {
        this.auctionId = auctionId;
        this.productList = productList;
        this.status = status;
        this.start = start;
        this.end = end;
        this.discount = discount;
    }

    public Auction() {
    }
}