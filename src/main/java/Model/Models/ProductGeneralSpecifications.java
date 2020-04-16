package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class ProductGeneralSpecifications implements Packable {

    private static List<ProductGeneralSpecifications> productGeneralSpecificationsList;

    static {

    }

    public enum InventoryStatus {
        InSock, Purchased, ToReceive, Sold, ToShip
    }

    private long generalSpecificationId;
    //    String name
//    String brand
//    double price
    private List<Field> fieldList;
    private List<Account> sellers;
    private InventoryStatus inventoryStatus;

    public long getGeneralSpecificationId() {
        return generalSpecificationId;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public List<Account> getSellers() {
        return sellers;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public static List<ProductGeneralSpecifications> getProductGeneralSpecificationsList() {
        return productGeneralSpecificationsList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public ProductGeneralSpecifications(long generalSpecificationId, List<Field> fieldList, List<Account> sellers, InventoryStatus inventoryStatus) {
        this.generalSpecificationId = generalSpecificationId;
        this.fieldList = fieldList;
        this.sellers = sellers;
        this.inventoryStatus = inventoryStatus;
    }
}
