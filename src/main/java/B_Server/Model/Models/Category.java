package B_Server.Model.Models;

import B_Server.Model.DataBase.DataBase;
import B_Server.Model.Models.Data.Data;
import Structs.FieldAndFieldList.Field;
import Exceptions.*;
import B_Server.Model.Tools.AddingNew;
import B_Server.Model.Tools.Packable;
import Structs.FieldAndFieldList.FieldList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category implements Packable<Category> , Cloneable {

    private static final Object staticLock = new Object();
    private final Object lock = new Object();

    /******************************************************fields*******************************************************/

    private static List<Category> list;

    private long categoryId;
    private String name;
    private FieldList categoryFields;
    private List<Long> subCategories;
    private List<Long> productList = new ArrayList<>();

    /*****************************************************setters*******************************************************/

    public static void setList(List<Category> list) {
        Category.list = list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubCategories(List<Long> subCategories) {
        this.subCategories = subCategories;
    }

    public void setCategoryFields(FieldList categoryFields) {
        this.categoryFields = categoryFields;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    /*****************************************************getters*******************************************************/

    public long getId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public FieldList getCategoryFields() {
        return categoryFields;
    }

    public List<Long> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public List<Long> getSubCategories() {
        return Collections.unmodifiableList(subCategories);
    }

    @NotNull
    @Contract(pure = true)
    public static List<Category> getList() {
        return Collections.unmodifiableList(list);
    }

    /**************************************************addAndRemove*****************************************************/

    public void addToProductList(long productId) {
        productList.add(productId);
        DataBase.save(this);
    }

    public void removeFromProductList(long productId) {
        productList.removeIf(pro -> productId == pro );
        DataBase.save(this);
    }

    public void addToSubCategoryList(long categoryId) {
        subCategories.add(categoryId);
        DataBase.save(this);
    }

    public static void addCategory(@NotNull Category category) {
        synchronized (staticLock) {
            category.setCategoryId(AddingNew.getRegisteringId().apply(Category.getList()));
            list.add(category);
            DataBase.save(category, true);
        }
    }

    public static void removeCategory(Category category) {
        list.removeIf(cat -> category.getId() == cat.getId());
        DataBase.remove(category);
    }

    /***************************************************otherMethods****************************************************/

    public static Category getCategoryById(long id) throws CategoryDoesNotExistException {
        return list.stream()
                .filter(category -> id == category.getId())
                .findFirst()
                .orElseThrow(() -> new CategoryDoesNotExistException(
                        "Category with the id:" + id + " does not exist in list of all categories."
                ));
    }

    public static void checkExistOfCategoryById(long id) throws CategoryDoesNotExistException {
        if (list.stream().noneMatch(category -> id == category.categoryId)) {
            throw new CategoryDoesNotExistException(
                    "Category with the id:" + id + " does not exist in list of all categories."
            );
        }
    }

    public void editField(@NotNull String fieldName, String value) throws FieldDoesNotExistException {

        synchronized (lock) {

            if (fieldName.equals("name")) {
                setName(value);
            } else {
                Field field = categoryFields.getFieldByName(fieldName);
                field.setString(value);
            }
        }
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Category> pack() {
        return new Data<Category>()
                .addField(categoryId)
                .addField(name)
                .addField(categoryFields)
                .addField(productList)
                .addField(subCategories)
                .setInstance(new Category());
    }

    @Override
    public Category dpkg(@NotNull Data<Category> data) {
        this.categoryId = (long) data.getFields().get(0);
        this.name = (String) data.getFields().get(1);
        this.categoryFields = (FieldList) data.getFields().get(2);
        this.productList = (List<Long>) data.getFields().get(3);
        this.subCategories = (List<Long>) data.getFields().get(4);
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Category(String name, FieldList categoryFields, List<Long> subCategories) {
        this.name = name;
        this.categoryFields = categoryFields;
        this.subCategories = subCategories;
    }

    private Category() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", categoryField=" + categoryFields +
                ", subCategoryList=" + subCategories +
                '}';
    }
}
