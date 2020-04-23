package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class Category implements Packable {

    private static final String categorySource
            = "src/main/resources/allCategories";

    private static List<Category> categoryList;

    static {

    }

    private long categoryId;
    private String name;
    private List<Product> productList;
    private List<Field> categoryField;
    private List<Category> subCategoryList;

    public long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<Field> getCategoryField() {
        return categoryField;
    }

    public List<Category> getSubCategoryList() {
        return subCategoryList;
    }

    public static List<Category> getCategoryList() {
        return categoryList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Category(long categoryId, String name, List<Product> productList, List<Field> categoryField, List<Category> subCategoryList) {
        this.categoryId = categoryId;
        this.name = name;
        this.productList = productList;
        this.categoryField = categoryField;
        this.subCategoryList = subCategoryList;
    }
}
