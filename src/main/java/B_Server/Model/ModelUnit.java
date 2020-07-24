package B_Server.Model;

import B_Server.Model.DataBase.DataBase;
import B_Server.Model.Models.*;
import B_Server.Model.Models.Structs.Medias;

import java.util.Collections;
import java.util.stream.Collectors;

public class ModelUnit {

    public static void preprocess_loadLists() {
        Cart.setList(Collections.synchronizedList(
                DataBase.loadList(Cart.class).stream().map(packable -> (Cart) packable).collect(Collectors.toList())));
        Account.setList(Collections.synchronizedList(
                DataBase.loadList(Account.class).stream().map(packable -> (Account) packable).collect(Collectors.toList())));
        Auction.setList(Collections.synchronizedList(
                DataBase.loadList(Auction.class).stream().map(packable -> (Auction) packable).collect(Collectors.toList())));
        Category.setList(Collections.synchronizedList(
                DataBase.loadList(Category.class).stream().map(packable -> (Category) packable).collect(Collectors.toList())));
        Product.setList(Collections.synchronizedList(
                DataBase.loadList(Product.class).stream().map(packable -> (Product) packable).collect(Collectors.toList())));
        Comment.setList(Collections.synchronizedList(
                DataBase.loadList(Comment.class).stream().map(packable -> (Comment) packable).collect(Collectors.toList())));
        DiscountCode.setList(Collections.synchronizedList(
                DataBase.loadList(DiscountCode.class).stream().map(packable -> (DiscountCode) packable).collect(Collectors.toList())));
        LogHistory.setList(Collections.synchronizedList(
                DataBase.loadList(LogHistory.class).stream().map(packable -> (LogHistory) packable).collect(Collectors.toList())));
        Request.setList(Collections.synchronizedList(
                DataBase.loadList(Request.class).stream().map(packable -> (Request) packable).collect(Collectors.toList())));
        Score.setList(Collections.synchronizedList(
                DataBase.loadList(Score.class).stream().map(packable -> (Score) packable).collect(Collectors.toList())));
        Medias.setList(Collections.synchronizedList(
                DataBase.loadList(Medias.class).stream().map(packable -> (Medias) packable).collect(Collectors.toList())));
        Offer.setList(Collections.synchronizedList(
                DataBase.loadList(Offer.class).stream().map(packable -> (Offer) packable).collect(Collectors.toList())));
    }
}
