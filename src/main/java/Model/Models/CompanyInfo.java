package Model.Models;

import Model.Tools.Data;

import java.util.List;

public class CompanyInfo implements Packable {

    private static List<CompanyInfo> companyInfoList;

    static {

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

    public static List<CompanyInfo> getCompanyInfoList() {
        return companyInfoList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public CompanyInfo(long companyId, FieldList fieldList) {
        this.companyId = companyId;
        this.fieldList = fieldList;
    }
}
