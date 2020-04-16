package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class PersonalInformation implements Packable {

    private static List<PersonalInformation> personalInformationList;

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

    public static List<PersonalInformation> getPersonalInformationList() {
        return personalInformationList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public PersonalInformation(long personalInformationId, List<Field> fieldList) {
        this.personalInformationId = personalInformationId;
        this.fieldList = fieldList;
    }
}
