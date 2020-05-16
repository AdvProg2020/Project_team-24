package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Models.Structs.Discount;
import Model.Tools.AddingNew;
import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Auction implements Packable<Auction>, ForPend {

    private static List<Auction> list;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static {
        list = DataBase.loadList("Auction").stream()
                .map(packable -> (Auction) packable)
                .collect(Collectors.toList());
    }

    /*****************************************************fields*******************************************************/

    private long auctionId;
    private String auctionName;
    private String stateForPend;
    private LocalDate start;
    private LocalDate end;
    private Discount discount;
    private List<Product> productList = new ArrayList<>();

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

    public String getStateForPend() {
        return stateForPend;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public Discount getDiscount() {
        return discount;
    }

    public String getAuctionName() {
        return auctionName;
    }

    /*****************************************************setters*******************************************************/

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setStateForPend(String stateForPend) {
        this.stateForPend = stateForPend;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void setAuctionId(long auctionId) {
        this.auctionId = auctionId;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addAuction(Auction auction) throws CanNotSaveToDataBaseException {
        auction.setAuctionId(AddingNew.getRegisteringId().apply(getList()));
        list.add(auction);
        DataBase.save(auction, true);
    }

    public static void removeAuction(Auction auction) throws CanNotRemoveFromDataBase {
        list.remove(auction);
        DataBase.remove(auction);
    }

    public void addProductToAuction(Product product) throws CanNotSaveToDataBaseException {
        productList.add(product);
        DataBase.save(this);
    }

    public void removeProductFromAuction(Product product) throws CanNotSaveToDataBaseException {
        productList.remove(product);
        DataBase.save(this);
    }

    /***************************************************otherMethods****************************************************/

    public static Auction getAuctionById(long id) throws AuctionDoesNotExistException {
        return list.stream()
                .filter(auction -> id == auction.getId())
                .findFirst()
                .orElseThrow(() -> new AuctionDoesNotExistException("Auction whit the id:" + id + " does Not Exist in Auction list."));
    }

    public static Auction getAuctionByName(String name) throws AuctionDoesNotExistException {
        return list.stream()
                .filter(auction -> name.equals(auction.getAuctionName()))
                .findFirst()
                .orElseThrow(() -> new AuctionDoesNotExistException("Auction whit the name:" + name + " does Not Exist in Auction list."));
    }

    public double getAuctionDiscount(double price) {
        return Math.min(discount.getPercent() * price / 100, discount.getAmount());
    }

    public Product getProductById(long id) throws ProductDoesNotExistException {
        return productList.stream()
                .filter(auction -> id == auction.getId())
                .findFirst()
                .orElseThrow(() -> new ProductDoesNotExistException("Product whit this id:" + id + " does Not Exist in Auction:" + getId() + " ."));
    }

    public void editField(String fieldName, String value) throws FieldDoesNotExistException, NumberFormatException {

        switch (fieldName) {
            case "auctionName":
                setAuctionName(value);
                break;
            case "start":
                setStart(LocalDate.parse(value, formatter));
                break;
            case "end":
                setEnd((LocalDate.parse(value, formatter)));
                break;
            case "stateForPend":
                setStateForPend(value);
                break;
            case "discountMaxAmount":
                discount.setAmount(Double.parseDouble(value));
                break;
            case "discountPercent":
                discount.setPercent(Double.parseDouble(value));
                break;
            default:
                throw new FieldDoesNotExistException(fieldName + " not found in auction.");
        }
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Auction> pack() {
        return new Data<Auction>()
                .addField(auctionId)
                .addField(productList.stream()
                        .map(Product::getId).collect(Collectors.toList()))
                .addField(stateForPend)
                .addField(start)
                .addField(end)
                .addField(discount)
                .setInstance(new Auction());
    }

    @Override
    public Auction dpkg(Data<Auction> data) throws ProductDoesNotExistException {
        this.auctionId = (long) data.getFields().get(0);
        List<Product> result = new ArrayList<>();
        for (Long aLong : ((List<Long>) data.getFields().get(1))) {
            Product productById = Product.getProductById(aLong);
            result.add(productById);
        }
        this.productList = result;
        this.stateForPend = (String) data.getFields().get(2);
        this.start = (LocalDate) data.getFields().get(3);
        this.end = (LocalDate) data.getFields().get(4);
        this.discount = (Discount) data.getFields().get(5);
        return this;
    }

    /**************************************************constructors*****************************************************/

    // doesn't need!
//    public Auction(long auctionId, List<Product> productList, String status, LocalDate start, LocalDate end, Discount discount) {
//        this.auctionId = auctionId;
//        this.productList = productList;
//        this.stateForPend = status;
//        this.start = start;
//        this.end = end;
//        this.discount = discount;
//    }

    public Auction(String auctionName, LocalDate start, LocalDate end, Discount discount) {
        this.auctionName = auctionName;
        this.start = start;
        this.end = end;
        this.discount = discount;
    }

    private Auction() {
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