package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class GoodSpecifications implements Packable {

    private static List<GoodSpecifications> goodSpecificationsList;

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

    public static List<GoodSpecifications> getGoodSpecificationsList() {
        return goodSpecificationsList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public GoodSpecifications(long goodSpecificationId, List<Field> fieldList) {
        this.goodSpecificationId = goodSpecificationId;
        this.fieldList = fieldList;
    }
}
