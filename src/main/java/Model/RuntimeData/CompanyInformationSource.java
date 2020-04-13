package Model.RuntimeData;

import Model.Models.CompanyInformation;
import Model.Tools.FileHandler;
import Model.Tools.PackClass;

import java.io.File;
import java.util.List;

public class CompanyInformationSource implements PackClass, FileHandler {

    private static File companyInformation_File = new File("src/main/resources/allCompanyInformation");

    private static List<CompanyInformation> companyInformationList;

    static {

    }

    public static List<CompanyInformation> getCompanyInformationList() {
        return companyInformationList;
    }
}
