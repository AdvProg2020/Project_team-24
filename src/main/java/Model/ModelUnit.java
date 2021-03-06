package Model;

import Model.DataBase.DataBase;
import Model.Models.*;
import Model.Models.Structs.Medias;

import java.util.stream.Collectors;

public class ModelUnit {

    private static ModelUnit modelUnit = new ModelUnit();

    public void preprocess_loadLists() {
        Cart.setList(DataBase.loadList(Cart.class).stream().map(packable -> (Cart) packable).collect(Collectors.toList()));
        Account.setList(DataBase.loadList(Account.class).stream().map(packable -> (Account) packable).collect(Collectors.toList()));
        Auction.setList(DataBase.loadList(Auction.class).stream().map(packable -> (Auction) packable).collect(Collectors.toList()));
        Category.setList(DataBase.loadList(Category.class).stream().map(packable -> (Category) packable).collect(Collectors.toList()));
        Product.setList(DataBase.loadList(Product.class).stream().map(packable -> (Product) packable).collect(Collectors.toList()));
        Comment.setList(DataBase.loadList(Comment.class).stream().map(packable -> (Comment) packable).collect(Collectors.toList()));
        DiscountCode.setList(DataBase.loadList(DiscountCode.class).stream().map(packable -> (DiscountCode) packable).collect(Collectors.toList()));
        LogHistory.setList(DataBase.loadList(LogHistory.class).stream().map(packable -> (LogHistory) packable).collect(Collectors.toList()));
        Request.setList(DataBase.loadList(Request.class).stream().map(packable -> (Request) packable).collect(Collectors.toList()));
        Score.setList(DataBase.loadList(Score.class).stream().map(packable -> (Score) packable).collect(Collectors.toList()));
        Medias.setList(DataBase.loadList(Medias.class).stream().map(packable -> (Medias) packable).collect(Collectors.toList()));
    }

    private ModelUnit() {
    }

    public static ModelUnit getInstance() {
        return modelUnit;
    }
}
