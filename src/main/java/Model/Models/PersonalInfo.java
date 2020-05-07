package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class PersonalInfo implements Packable {

    private static List<PersonalInfo> list;

    static {
        DataBase.loadList(PersonalInfo.class);
    }

    private long id;
    //    String firstName
    //    String lastName
    //    String email
    //    String phoneNumber
    private String address;
    private String postCode;
    private FieldList fieldList;

    public long getId() {
        return id;
    }

    public FieldList getFieldList() {
        return fieldList;
    }

    @Override
    public Data pack() {
        return new Data(PersonalInfo.class.getName())
                .addField(id)
                .addField(fieldList);
    }

    @Override
    public void dpkg(Data data) {
        this.id = (long) data.getFields().get(0);
        this.fieldList = (FieldList) data.getFields().get(1);
    }

    public static PersonalInfo getPersonalInfoById(long id) {
        return list.stream()
                .filter(personalInfo -> id == personalInfo.getId())
                .findFirst()
                .orElseThrow();
    }

    public PersonalInfo(long id, FieldList fieldList) {
        this.id = id;
        this.fieldList = fieldList;
    }

    public PersonalInfo(){}
    ///yac

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
