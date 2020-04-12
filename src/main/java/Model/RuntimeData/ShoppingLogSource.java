package Model.RuntimeData;

import Model.Models.LogHistories.ShoppingLog;

import java.io.File;
import java.util.List;

public class ShoppingLogSource {

    private static File shoppingLogList_File = new File("src/main/resources/allShoppingLogs");

    private List<ShoppingLog> shoppingLogList;

    public List<ShoppingLog> getShoppingLogList() {
        return shoppingLogList;
    }

    public ShoppingLogSource(List<ShoppingLog> shoppingLogList) {
        this.shoppingLogList = shoppingLogList;
    }
}
