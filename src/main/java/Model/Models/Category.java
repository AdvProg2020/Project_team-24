package Model.Models;

import java.util.List;

public class Category {

    public long categoryId;
    private String name;
    private List<Category> subCategories;
    private List<Product> products;

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Category(long categoryId, String name, List<Category> subCategories, List<Product> products) {
        this.categoryId = categoryId;
        this.name = name;
        this.subCategories = subCategories;
        this.products = products;
    }
}
