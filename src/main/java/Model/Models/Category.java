package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.List;

public class Category implements Packable {

    public long categoryId;
    private String name;
    private List<String> categorySpecifications;
    private List<Category> subCategories;

    public String getName() {
        return name;
    }

    public List<String> getCategorySpecifications() {
        return categorySpecifications;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(categoryId, categorySpecifications, subCategories);
    }

    public Category(long categoryId, String name, List<String> categorySpecifications, List<Category> subCategories) {
        this.categoryId = categoryId;
        this.name = name;
        this.categorySpecifications = categorySpecifications;
        this.subCategories = subCategories;
    }
}
