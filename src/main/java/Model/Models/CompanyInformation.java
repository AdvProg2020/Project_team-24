package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class CompanyInformation implements Packable {

    private static List<CompanyInformation> companyInformationList;

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

    public static List<CompanyInformation> getCompanyInformationList() {
        return companyInformationList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public CompanyInformation(long companyId, FieldList fieldList) {
        this.companyId = companyId;
        this.fieldList = fieldList;
    }
}
