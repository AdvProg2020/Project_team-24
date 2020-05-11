package Model.Models;

import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Category implements Packable {

    private static List<Category> list;

    static {
        DataBase.loadList(Category.class);
    }

    /*****************************************************fields*******************************************************/

    private long categoryId;
    private String name;
    private FieldList categoryField;
    private List<Product> productList;
    private List<Category> subCategoryList;

    /*****************************************************getters*******************************************************/

    public long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public FieldList getCategoryField() {
        return categoryField;
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public List<Category> getSubCategoryList() {
        return Collections.unmodifiableList(subCategoryList);
    }

    public static List<Category> getList() {
        return Collections.unmodifiableList(list);
    }

    /**************************************************addAndRemove*****************************************************/

    public void addToProductList(Product product) {
        productList.add(product);
        DataBase.save(this);
    }

    public void removeFromProductList(Product product) {
        productList.remove(product);
        DataBase.remove(this);
    }

    public void addToSubCategoryList(Category category) {
        subCategoryList.add(category);
        DataBase.save(category);
    }

    public void removeFromSubCategoryList(Category category) {
        subCategoryList.remove(category);
        DataBase.remove(category);
    }

    public static void addCategory(Category category) {
        list.add(category);
        DataBase.save(category);
    }

    public static void removeCategory(Category category) {
        list.remove(category);
        DataBase.remove(category);
    }

    /***************************************************otherMethods****************************************************/

    public static Category getCategoryById(long id) {
        return list.stream()
                .filter(category -> id == category.getCategoryId())
                .findFirst()
                .orElseThrow(); // need category not found exception.
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data pack() {
        return new Data(Category.class.getName())
                .addField(categoryId)
                .addField(name)
                .addField(categoryField)
                .addField(productList.stream()
                .map(Product::getProductId)
                .collect(Collectors.toList()))
                .addField(subCategoryList.stream()
                .map(Category::getCategoryId)
                .collect(Collectors.toList()));
    }

    @Override
    public void dpkg(Data data) throws ProductDoesNotExistException {
        this.categoryId = (long) data.getFields().get(0);
        this.name = (String) data.getFields().get(1);
        this.categoryField = (FieldList) data.getFields().get(2);
        List<Product> result_1 = new ArrayList<>();
        for (Long aLong : (List<Long>) data.getFields().get(3)) {
            Product productById = Product.getProductById(aLong);
            result_1.add(productById);
        }
        this.productList = result_1;
        List<Category> result_2 = new ArrayList<>();
        for (Long aLong : (List<Long>) data.getFields().get(3)) {
            Category CategoryById = Category.getCategoryById(aLong);
            result_2.add(CategoryById);
        }
        this.subCategoryList = result_2;
    }

    /**************************************************constructors*****************************************************/

    public Category(long categoryId, String name, List<Product> productList, FieldList categoryField, List<Category> subCategoryList) {
        this.categoryId = categoryId;
        this.name = name;
        this.productList = productList;
        this.categoryField = categoryField;
        this.subCategoryList = subCategoryList;
    }

    public Category() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", categoryField=" + categoryField +
                ", subCategoryList=" + subCategoryList +
                '}';
    }
}
