package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;

import java.util.List;

public class ProductInfo implements Packable {

    private static List<ProductInfo> productInfoList;

    static {
        DataBase.preprocess(PersonalInfo.class);
    }

    public enum InventoryStatus {
        InSock, Purchased, ToReceive, Sold, ToShip
    }

    private long generalSpecificationId;
    //    String name
    //    String brand
    private double price;
    private FieldList fieldList;
    private List<Account> sellers;
    private InventoryStatus inventoryStatus;

    public long getGeneralSpecificationId() {
        return generalSpecificationId;
    }

    public FieldList getFieldList() {
        return fieldList;
    }

    public List<Account> getSellers() {
        return sellers;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public static List<ProductInfo> getProductInfoList() {
        return productInfoList;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public ProductInfo(long generalSpecificationId, double price, FieldList fieldList, List<Account> sellers, InventoryStatus inventoryStatus) {
        this.generalSpecificationId = generalSpecificationId;
        this.price = price;
        this.fieldList = fieldList;
        this.sellers = sellers;
        this.inventoryStatus = inventoryStatus;
    }
}
