package Model.RuntimeData;

import Model.Models.CompanyInformation;

import java.io.File;
import java.util.List;

public class CompanyInformationSource {

    private static File companyInformation_File = new File("src/main/resources/allCompanyInformation");

    private static List<CompanyInformation> companyInformationList;

    static {

    }

    public static List<CompanyInformation> getCompanyInformationList() {
        return companyInformationList;
    }
}
