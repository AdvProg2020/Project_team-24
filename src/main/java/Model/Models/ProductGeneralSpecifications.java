package Model.Models;

import java.util.List;

public class ProductGeneralSpecifications {

    public enum InventoryStatus {
        InSock,Purchased,ToReceive,Sold,ToShip
    }

    public long generalSpecificationId;
    private String name;
    private String brand;
    private double price;
    private List<Account> sellers;
    private InventoryStatus inventoryStatus;

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public List<Account> getSellers() {
        return sellers;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public ProductGeneralSpecifications(long generalSpecificationId, String name, String brand, double price, List<Account> sellers, InventoryStatus inventoryStatus) {
        this.generalSpecificationId = generalSpecificationId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.sellers = sellers;
        this.inventoryStatus = inventoryStatus;
    }
}
