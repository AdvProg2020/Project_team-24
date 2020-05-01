package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class CompanyInfo implements Packable {

    private static List<CompanyInfo> list;

    static {
        DataBase.loadList(CompanyInfo.class);
    }

    private long companyId;
    //    String name
    //    String phoneNumber
    //    String email
    //    String foundation
    private FieldList fieldList;

    public FieldList getFieldList() {
        return fieldList;
    }

    public long getCompanyId() {
        return companyId;
    }

    public static List<CompanyInfo> getList() {
        return list;
    }

    @Override
    public Data pack() {
        return new Data(CompanyInfo.class.getName())
                .addField(companyId)
                .addField(fieldList);
    }

    @Override
    public void dpkg(Data data) {
        this.companyId = (long) data.getFields().get(0);
        this.fieldList = (FieldList) data.getFields().get(2);
    }

    public static CompanyInfo getCompanyInfoById(long id) {
        return list.stream()
                .filter(companyInfo -> id == companyInfo.companyId)
                .findFirst()
                .orElseThrow();
    }

    public CompanyInfo(long companyId, FieldList fieldList) {
        this.companyId = companyId;
        this.fieldList = fieldList;
    }

    public CompanyInfo() {
    }
}
