package B_Server.Model.Models;

import B_Server.Model.DataBase.DataBase;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Seller;
import B_Server.Model.Models.Data.Data;
import B_Server.Model.Tools.AddingNew;
import B_Server.Model.Tools.Packable;
import Exceptions.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Offer implements Packable<Offer> {

    private static List<Offer> list = new ArrayList<>();

    static {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(Offer::DeleteExpiredOffers, 0, 12, TimeUnit.HOURS);
    }

    private static ThreadLocal<String> bestOffer_User = new ThreadLocal<>();
    private static ThreadLocal<Double> bestOfferPrice = new ThreadLocal<>();

    private static void DeleteExpiredOffers() {
        list.forEach(offer -> {

            if (!offer.getEndTm().isAfter(LocalDate.now())) return;

            Map<String, Double> auctioneersPrices = offer.getAuctioneersPrices();
            auctioneersPrices.forEach((key, value) -> {

                if(bestOfferPrice.get() < value) {
                    bestOffer_User.set(key);
                    bestOfferPrice.set(value);
                }
            });

            try {

                offer.getProduct().setInit_Price(offer.getProduct().getProductOfSellerById(offer.getSeller().getId()).getPrice());
                offer.getProduct().getProductOfSellerById(offer.getSeller().getId()).setPrice(bestOfferPrice.get());
                Customer customer = (Customer) Account.getAccountByUserName(bestOffer_User.get());
                customer.getCart().addProductToCart(offer.getSeller().getId(),
                        offer.getProduct().getId());

                DataBase.save(customer.getCart());
                DataBase.save(offer.getProduct());

            } catch (AccountDoesNotExistException | SellerDoesNotSellOfThisProduct ignore) { }

            bestOfferPrice.set(0D);
        });

        list.removeIf(offer -> offer.getEndTm().isAfter(LocalDate.now()));
    }

    private static final Object staticLock = new Object();

    /*****************************************************fields*******************************************************/

    private long offerId;
    private Product product;
    private LocalDate start;
    private LocalDate endTm;
    private Seller seller;
    private Map<String, Double> auctioneersPrices = new HashMap<>();

    public static Offer getOfferById(long offerId) {
        return list.stream().filter(offer -> offer.getId() == offerId)
                .findFirst().orElse(null);
    }

    private void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public static void setList(List<Offer> list) {
        Offer.list = list;
    }

    public static List<Offer> getList() {
        return list;
    }

    @Override
    public long getId() {
        return offerId;
    }

    public Product getProduct() {
        return product;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEndTm() {
        return endTm;
    }

    public Seller getSeller() {
        return seller;
    }

    public Map<String, Double> getAuctioneersPrices() {
        return auctioneersPrices;
    }

    public static void addOffer(@NotNull Offer offer) {
        synchronized (staticLock) {
            offer.setOfferId(AddingNew.getRegisteringId().apply(list));
            list.add(offer);
            DataBase.save(offer, true);
        }
    }

    @Override
    public Data<Offer> pack() {
        return new Data<Offer>()
                .addField(offerId)
                .addField(product.getId())
                .addField(start)
                .addField(endTm)
                .addField(seller.getId())
                .addField(auctioneersPrices)
                .setInstance(new Offer());
    }

    @Override
    public Offer dpkg(@NotNull Data<Offer> data) throws ProductDoesNotExistException, AccountDoesNotExistException {
        offerId = (long) data.getFields().get(0);
        product = Product.getProductById((long) data.getFields().get(1));
        start = (LocalDate) data.getFields().get(2);
        endTm = (LocalDate) data.getFields().get(3);
        seller = (Seller) Account.getAccountById((long) data.getFields().get(4));
        auctioneersPrices = (Map<String, Double>) data.getFields().get(5);
        return this;
    }

    public Offer(Product product, LocalDate start, LocalDate endTm, Seller seller) {
        this.product = product;
        this.start = start;
        this.endTm = endTm;
        this.seller = seller;
    }

    private Offer() {
    }
}
