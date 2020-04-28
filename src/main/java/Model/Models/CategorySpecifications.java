package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class CategorySpecifications implements Packable {

    private static List<CategorySpecifications> categorySpecificationsList;

    static {
        DataBase.loadList(CategorySpecifications.class);
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
    public Data pack() {
        return null;
    }

    @Override
    public void dpkg(Data data) {

    }

    public CategorySpecifications(long goodSpecificationId, FieldList fieldList) {
        this.goodSpecificationId = goodSpecificationId;
        this.fieldList = fieldList;
    }
}
