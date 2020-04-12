package Model.RuntimeData;

import Model.Models.Category;

import java.io.File;
import java.util.List;

public class CategorySource {

    private static File categoryList_File = new File("src/main/resources/allCategories");

    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public CategorySource(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
