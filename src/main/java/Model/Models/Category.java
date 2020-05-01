package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class Category implements Packable {

    private static List<Category> categoryList;

    static {
        DataBase.loadList(Category.class);
    }

    private long categoryId;
    private String name;
    private List<Product> productList;
    private FieldList categoryField;
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

    public FieldList getCategoryField() {
        return categoryField;
    }

    public List<Category> getSubCategoryList() {
        return subCategoryList;
    }

    public static List<Category> getCategoryList() {
        return categoryList;
    }

    @Override
    public Data pack() {
        return null;
    }

    @Override
    public void dpkg(Data data) {

    }

    public Category(long categoryId, String name, List<Product> productList, FieldList categoryField, List<Category> subCategoryList) {
        this.categoryId = categoryId;
        this.name = name;
        this.productList = productList;
        this.categoryField = categoryField;
        this.subCategoryList = subCategoryList;
    }

    public Category() {
    }
}
