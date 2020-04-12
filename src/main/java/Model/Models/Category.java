package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.List;

public class Category implements Packable {

    public long categoryId;
    private String name;
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

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(categoryId, categorySpecifications, subCategoryList);
    }

    public Category(long categoryId, String name, List<String> categorySpecifications, List<Category> subCategoryList) {
        this.categoryId = categoryId;
        this.name = name;
        this.categorySpecifications = categorySpecifications;
        this.subCategoryList = subCategoryList;
    }
}
