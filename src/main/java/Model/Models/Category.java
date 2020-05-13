package Model.Models;

import Exceptions.CanNotAddException;
import Exceptions.CanNotRemoveException;
import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Category implements Packable<Category> {

    private static List<Category> list;

    private static List<String> fieldNames = Collections.singletonList("name");

    static {
        list = DataBase.loadList("Category").stream()
                .map(packable -> (Category) packable)
                .collect(Collectors.toList());
    }

    /*****************************************************fields*******************************************************/

    private long categoryId;
    private String name;
    private FieldList categoryField;
    private List<Product> productList;
    private List<Category> subCategoryList;

    /*****************************************************getters*******************************************************/

    public long getId() {
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

    public void addToProductList(Product product) throws CanNotAddException {
        productList.add(product);
        DataBase.save(this);
    }

    public void removeFromProductList(Product product) throws CanNotRemoveException {
        productList.remove(product);
        DataBase.remove(this);
    }

    public void addToSubCategoryList(Category category) throws CanNotAddException {
        subCategoryList.add(category);
        DataBase.save(this);
    }

    public void removeFromSubCategoryList(Category category) throws CanNotRemoveException {
        subCategoryList.remove(category);
        DataBase.remove(category);
    }

    public static void addCategory(Category category) throws CanNotAddException {
        list.add(category);
        DataBase.save(category, true);
    }

    public static void removeCategory(Category category) throws CanNotRemoveException{
        list.remove(category);
        DataBase.remove(category);
    }

    /***************************************************otherMethods****************************************************/

    public static Category getCategoryById(long id) {
        return list.stream()
                .filter(category -> id == category.getId())
                .findFirst()
                .orElseThrow(); // need category not found exception.
    }

    public static Category getCategoryByName(String name){
        return list.stream()
                .filter(category -> name.equals(category.getName()))
                .findFirst()
                .orElseThrow(); // need new exception.
    }

    public Field getClassFieldByName(String name) throws NoSuchFieldException {
        if (!fieldNames.contains(name)) {
            throw new NoSuchFieldException();
        } else return this.getClass().getField(name);
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Category> pack() {
        return new Data<Category>()
                .addField(categoryId)
                .addField(name)
                .addField(categoryField)
                .addField(productList.stream()
                .map(Product::getId)
                .collect(Collectors.toList()))
                .addField(subCategoryList.stream()
                .map(Category::getId)
                .collect(Collectors.toList()))
                .setInstance(new Category());
    }

    @Override
    public Category dpkg(Data<Category> data) throws ProductDoesNotExistException {
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
        return this;
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
