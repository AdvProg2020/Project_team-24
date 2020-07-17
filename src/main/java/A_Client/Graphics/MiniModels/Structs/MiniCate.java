package A_Client.Graphics.MiniModels.Structs;

import A_Client.Graphics.MiniModels.FieldAndFieldList.FieldList;

public class MiniCate {

    private final String cateId;
    private final String cateName;
    private final FieldList fieldList;

    public String getCateName() {
        return cateName;
    }

    public FieldList getFieldList() {
        return fieldList;
    }

    public String getCateId() {
        return cateId;
    }

    public MiniCate(String cateId, String cateName, FieldList fieldList) {
        this.cateId = cateId;
        this.cateName = cateName;
        this.fieldList = fieldList;
    }
}
