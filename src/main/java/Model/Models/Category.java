package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Models.Field.Fields.SingleString;
import Model.Tools.AddingNew;
import Model.Tools.Data;
import Model.Tools.Packable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class Category implements Packable<Category> , Cloneable {

    /******************************************************fields*******************************************************/

    private static List<Category> list;

    private long categoryId;
    private String name;
    private FieldList categoryFields;
    private List<Long> productList;
    private List<Long> subCategories;

    /*****************************************************setters*******************************************************/

    public static void setList(List<Category> list) {
        Category.list = list;
    }

    public void setName(String name) {
        this.name = name;
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

    public void addToSubCategoryList(long categoryId) {
        subCategories.add(categoryId);
        DataBase.save(this);
    }

    public static void addCategory(@NotNull Category category) {
        category.setCategoryId(AddingNew.getRegisteringId().apply(Category.getList()));
        list.add(category);
        DataBase.save(category, true);
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

    public void editField(@NotNull String fieldName, String value) throws FieldDoesNotExistException {

        if (fieldName.equals("name")) {
            setName(value);
        } else {
            SingleString field = (SingleString) categoryFields.getFieldByName(fieldName);
            field.setString(value);
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
        this.subCategories = (List<Long>) data.getFields().get(3);
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Category(String name, List<Long> productList, FieldList categoryFields, List<Long> subCategories) {
        this.name = name;
        this.productList = productList;
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
