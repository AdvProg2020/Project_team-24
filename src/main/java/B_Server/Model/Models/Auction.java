package B_Server.Model.Models;

import B_Server.Model.DataBase.DataBase;
import B_Server.Model.Models.Data.Data;
import B_Server.Model.Models.Structs.Discount;
import Exceptions.*;
import B_Server.Model.Tools.AddingNew;
import B_Server.Model.Tools.ForPend;
import B_Server.Model.Tools.Packable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Auction implements Packable<Auction>, ForPend, Cloneable {

    /*****************************************************fields*******************************************************/

    private static List<Auction> list;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private long auctionId;
    private String auctionName;
    private String stateForPend;
    private LocalDate start;
    private LocalDate end;
    private Discount discount;
    private List<Long> productList;

    /*****************************************************getters*******************************************************/

    @NotNull
    @Contract(pure = true)
    public static List<Auction> getList() {
        return Collections.unmodifiableList(list);
    }

    public List<Long> getProductList() {
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

    public String getName() {
        return auctionName;
    }

    /*****************************************************setters*******************************************************/

    public void setProductList(List<Long> productList) {
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

    public static void setList(List<Auction> list) {
        Auction.list = list;
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addAuction(@NotNull Auction auction, boolean New) {
        if (New) auction.setAuctionId(AddingNew.getRegisteringId().apply(getList()));
        list.add(auction);
        DataBase.save(auction, true);
    }

    public static void removeAuction(Auction auction) {
        list.removeIf(auc -> auction.getId() == auc.getId());
        DataBase.remove(auction);
    }

    public void addProductToAuction(long productId) {
        productList.add(productId);
        DataBase.save(this);
    }

    public void removeProductFromAuction(long productId) {
        productList.remove(productId);
        DataBase.save(this);
    }

    /***************************************************otherMethods****************************************************/

    public static Auction getAuctionById(long id) throws AuctionDoesNotExistException {
        return list.stream()
                .filter(product -> id == product.getId())
                .findFirst()
                .orElseThrow(() -> new AuctionDoesNotExistException(
                        "Auction does not exist with this id:" + id + " in list of all Auctions."
                ));
    }

    public static void checkExistOfAuctionById(long id , @NotNull List<Long> longList, Packable<?> packable) throws AuctionDoesNotExistException {
        if (longList.stream().noneMatch(Id -> id == Id)) {
            throw new AuctionDoesNotExistException(
                    "In the " + packable.getClass().getSimpleName() + " with id:" + packable.getId() + " the Auction with id:"+  id + " does not exist."
            );
        }
    }

    public double getAuctionDiscount(double price) {
        return Math.min(discount.getPercent() * price / 100, discount.getAmount());
    }

    public void editField(@NotNull String fieldName, String value) throws FieldDoesNotExistException, NumberFormatException {

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
                throw new FieldDoesNotExistException("Field with the name:" + fieldName + " doesn't exist.");
        }
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Auction> pack() {
        return new Data<Auction>()
                .addField(auctionId)
                .addField(productList)
                .addField(stateForPend)
                .addField(start)
                .addField(end)
                .addField(discount)
                .setInstance(new Auction());
    }

    @Override
    public Auction dpkg(@NotNull Data<Auction> data) {
        this.auctionId = (long) data.getFields().get(0);
        this.productList = (List<Long>) data.getFields().get(1);
        this.stateForPend = (String) data.getFields().get(2);
        this.start = (LocalDate) data.getFields().get(3);
        this.end = (LocalDate) data.getFields().get(4);
        this.discount = (Discount) data.getFields().get(5);
        return this;
    }

    /**************************************************constructors*****************************************************/

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
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

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