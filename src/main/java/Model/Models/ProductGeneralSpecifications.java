package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.List;

public class ProductGeneralSpecifications implements Packable {

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

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(generalSpecificationId, inventoryStatus, name, brand, price, sellers);
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
