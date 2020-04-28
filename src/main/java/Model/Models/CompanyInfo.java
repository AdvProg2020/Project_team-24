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
        return null;
    }

    @Override
    public void dpkg(Data data) {

    }

    public CompanyInfo(long companyId, FieldList fieldList) {
        this.companyId = companyId;
        this.fieldList = fieldList;
    }
}
