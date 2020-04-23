package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class CompanyInformation implements Packable {

    private static final String source
            = "src/main/resources/allCompanyInformation";

    private static List<CompanyInformation> companyInformationList;

    static {

    }

    private long companyId;
    //    String name
//    String phoneNumber
//    String email
//    Date foundation
    private List<Field> fieldList;

    public List<Field> getFieldList() {
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

    public CompanyInformation(long companyId, List<Field> fieldList) {
        this.companyId = companyId;
        this.fieldList = fieldList;
    }
}
