package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.List;

public class Category implements Packable {

    public long categoryId;
    private String name;
    private List<Product> productList;
    private List<String> categorySpecifications;
    private List<Category> subCategoryList;

    public String getName() {
        return name;
    }

    public List<String> getCategorySpecifications() {
        return categorySpecifications;
    }

    public List<Category> getSubCategoryList() {
        return subCategoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(categoryId, categorySpecifications, subCategoryList, productList);
    }

    public Category(long categoryId, String name, List<Product> productList, List<String> categorySpecifications, List<Category> subCategoryList) {
        this.categoryId = categoryId;
        this.name = name;
        this.productList = productList;
        this.categorySpecifications = categorySpecifications;
        this.subCategoryList = subCategoryList;
    }
}
