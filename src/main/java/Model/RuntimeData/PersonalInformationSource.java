package Model.RuntimeData;

import Model.Models.PersonalInformation;

import java.io.File;
import java.util.List;

public class PersonalInformationSource {

    private static File personalInformationList_File = new File("src/main/resources/allPersonalInformation");

    private List<PersonalInformation> personalInformationList;

    public List<PersonalInformation> getPersonalInformationList() {
        return personalInformationList;
    }

    public PersonalInformationSource(List<PersonalInformation> personalInformationList) {
        this.personalInformationList = personalInformationList;
    }
}
