package Modols.Category;

import Modols.Product.Product;

import java.util.List;

public class Category {

    protected int categoryId;
    protected String name;
    protected List<Category> subCategories;
    protected List<Product> products;

    //

    public Category(int categoryId, String name, List<Category> subCategories, List<Product> products) {
        this.categoryId = categoryId;
        this.name = name;
        this.subCategories = subCategories;
        this.products = products;
    }
}
