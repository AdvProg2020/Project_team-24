package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class PersonalInfo implements Packable {

    private static final String source
            = "src/main/resources/allPersonalInformation";

    private static List<PersonalInfo> personalInfoList;

    static {

    }

    private long personalInformationId;
    //    String firstName
//    String lastName
//    String email
//    String phoneNumber
    private List<Field> fieldList;

    public long getPersonalInformationId() {
        return personalInformationId;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public static List<PersonalInfo> getPersonalInfoList() {
        return personalInfoList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public PersonalInfo(long personalInformationId, List<Field> fieldList) {
        this.personalInformationId = personalInformationId;
        this.fieldList = fieldList;
    }
}
