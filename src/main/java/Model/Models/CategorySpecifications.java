package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class CategorySpecifications implements Packable {

    private static List<CategorySpecifications> categorySpecificationsList;

    static {

    }

    private long goodSpecificationId;
    private List<Field> fieldList;

    public long getGoodSpecificationId() {
        return goodSpecificationId;
    }

    public List<Field> getFieldList() {
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

    public CategorySpecifications(long goodSpecificationId, List<Field> fieldList) {
        this.goodSpecificationId = goodSpecificationId;
        this.fieldList = fieldList;
    }
}
