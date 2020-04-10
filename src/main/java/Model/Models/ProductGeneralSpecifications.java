package Model.Models;

import Model.ModelUnit.Account;

import java.util.List;

public class ProductGeneralSpecifications {

    protected int generalSpecificationId;
    protected String name;
    protected String brand;
    protected double price;
    protected List<Account> sellers;
    protected InventoryStatus inventoryStatus;

    //

    public ProductGeneralSpecifications(int generalSpecificationId, String name, String brand, double price, List<Account> sellers, InventoryStatus inventoryStatus) {
        this.generalSpecificationId = generalSpecificationId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.sellers = sellers;
        this.inventoryStatus = inventoryStatus;
    }

    enum InventoryStatus {
        InSock,Purchased,ToReceive,Sold,ToShip
    }
}
