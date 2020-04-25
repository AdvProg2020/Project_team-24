package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;

import java.util.List;

public class PersonalInfo implements Packable {

    private static List<PersonalInfo> personalInfoList;

    static {
        DataBase.preprocess(PersonalInfo.class);
    }

    private long personalInformationId;
    //    String firstName
    //    String lastName
    //    String email
    //    String phoneNumber
    private FieldList fieldList;

    public long getPersonalInformationId() {
        return personalInformationId;
    }

    public FieldList getFieldList() {
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

    public PersonalInfo(long personalInformationId, FieldList fieldList) {
        this.personalInformationId = personalInformationId;
        this.fieldList = fieldList;
    }
}
