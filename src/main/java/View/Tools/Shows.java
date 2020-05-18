package View.Tools;

import Model.Models.Account;
import Model.Models.Auction;
import Model.Models.Field.Fields.SingleString;
import Model.Models.Info;
import Model.Models.Product;

import java.util.function.Function;

public class Shows {

    private static Function<Product, String> showProduct = product ->
            "----------------------------------------------" + System.lineSeparator() +
                    "Product info:" + System.lineSeparator() +
                    String.format("ProductId:%d \nProductName:%s \n", product.getId(), product.getName()) +
                    String.format("ProductCategoryId:%d \nProductCategoryName:%s \n", product.getCategory().getId(), product.getCategory().getName()) +
                    (product.getAuction() == null ? "" : String.format("ProductAuctionId:%d \nProductAuctionName:%s \n", product.getAuction().getId(), product.getAuction().getName())) +
                    "Prices and Sellers:" + System.lineSeparator() +
                    product.getSellersOfProduct().stream().map(productOfSeller ->
                            String.format(
                                    "SellerId:%d Number:%d %s \n", productOfSeller.getSellerId(), productOfSeller.getNumber(),
                                    (product.getAuction() == null ? "Price:" + productOfSeller.getPrice() : String.format("Old price:%f newPrice:%f",
                                            productOfSeller.getPrice(), productOfSeller.getPrice() - product.getAuction().getAuctionDiscount(productOfSeller.getPrice())))
                            )).reduce("", (a, b) -> a + b) + "----------------------------------------------";

    private static Function<Auction, String> showAuction = auction ->
            "----------------------------------------------" + System.lineSeparator() +
                    String.format("ProductAuctionId:%d \nProductAuctionName:%s \n", auction.getId(), auction.getName()) +
                    String.format("Start:%s \nEnd:%s \n", auction.getStart(), auction.getEnd()) +
                    String.format("Discount percent:%f \nDiscount limit:%f \n", auction.getDiscount().getPercent(), auction.getDiscount().getAmount()) +
                    "----------------------------------------------";

    private static Function<Account, String> showAccount = account ->
            "----------------------------------------------" + System.lineSeparator() +
                    String.format("AccountId:%d \nAccountUsername:%s \n", account.getId(), account.getUserName()) +
                    String.format("AccountType:%s \nAccountRegisterDate:%s \n", account.getPersonalInfo().getSubject(), account.getPersonalInfo().getUploadDate()) +
                    account.getPersonalInfo().getList().getFieldList().stream().map(
                            field -> String.format("%s : %s\n", field.getFieldName(), ((SingleString) field).getString())
                    ).reduce("", (a, b) -> a + b) + "----------------------------------------------";

    private static Function<Info, String> showInfo = info ->
            "----------------------------------------------" + System.lineSeparator() +
                    info.getList().getFieldList().stream().map(
                            field -> String.format("%s : %s\n", field.getFieldName(), ((SingleString) field).getString())
                    ).reduce("", (a, b) -> a + b) + "----------------------------------------------";

    public static Function<Product, String> getShowProduct() {
        return showProduct;
    }

    public static Function<Auction, String> getShowAuction() {
        return showAuction;
    }

    public static Function<Account, String> getShowAccount() {
        return showAccount;
    }

    public static Function<Info, String> getShowInfo() {
        return showInfo;
    }
}
