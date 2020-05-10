package Model.Models;

import Exceptions.AccountDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductInfo implements Packable {

    private static List<ProductInfo> list;

    static {
        DataBase.loadList(PersonalInfo.class);
    }

    public enum InventoryStatus {
        InSock, Purchased, ToReceive, Sold, ToShip
    }

    private long id;
    //    String name
    //    String brand
    private double price;
    private FieldList fieldList;
    private List<Account> sellers;
    private InventoryStatus inventoryStatus;

    public long getId() {
        return id;
    }

    // add seller method.

    public FieldList getFieldList() {
        return fieldList;
    }

    public List<Account> getSellers() {
        return sellers;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public static List<ProductInfo> getList() {
        return list;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public Data pack() {
        return new Data(ProductInfo.class.getName())
                .addField(id)
                .addField(price)
                .addField(fieldList)
                .addField(inventoryStatus)
                .addField(sellers.stream()
                .map(Account::getId).collect(Collectors.toList()));
    }

    @Override
    public void dpkg(Data data) throws AccountDoesNotExistException {
        this.id = (long) data.getFields().get(0);
        this.price = (double) data.getFields().get(1);
        this.fieldList = (FieldList) data.getFields().get(2);
        this.inventoryStatus = (InventoryStatus) data.getFields().get(3);
        List<Account> result = new ArrayList<>();
        for (Long aLong : Collections.unmodifiableList((List<Long>) data.getFields().get(4))) {
            Account accountById = Account.getAccountById(aLong);
            result.add(accountById);
        }
        this.sellers = result;
    }

    // override clone. for all.

    public static ProductInfo getProductInfoById(long id) {
        return list.stream()
                .filter(productInfo -> id == productInfo.getId())
                .findFirst()
                .orElseThrow();
    }

    public ProductInfo(long id, double price, FieldList fieldList, List<Account> sellers, InventoryStatus inventoryStatus) {
        this.id = id;
        this.price = price;
        this.fieldList = fieldList;
        this.sellers = sellers;
        this.inventoryStatus = inventoryStatus;
    }

    public ProductInfo() {
    }
}
