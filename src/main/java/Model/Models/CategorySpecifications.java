package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;

import java.util.List;

public class CategorySpecifications implements Packable {

    private static List<CategorySpecifications> categorySpecificationsList;

    static {
        DataBase.preprocess(CategorySpecifications.class);
    }

    private long goodSpecificationId;
    private FieldList fieldList;

    public long getGoodSpecificationId() {
        return goodSpecificationId;
    }

    public FieldList getFieldList() {
        return fieldList;
    }

    public static List<CategorySpecifications> getCategorySpecificationsList() {
        return categorySpecificationsList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public CategorySpecifications(long goodSpecificationId, FieldList fieldList) {
        this.goodSpecificationId = goodSpecificationId;
        this.fieldList = fieldList;
    }
}
