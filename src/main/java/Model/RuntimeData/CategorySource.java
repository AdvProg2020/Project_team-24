package Model.RuntimeData;

import Model.Models.Category;
import Model.Tools.FileHandler;

import java.io.File;
import java.util.List;

public class CategorySource implements PackClass, FileHandler {

    private static File categoryList_File = new File("src/main/resources/allCategories");

    private static List<Category> categoryList;

    static {

    }

    public static List<Category> getCategoryList() {
        return categoryList;
    }
}
