package Model.Models;

import Model.DataBase.DataBase;
import Model.Models.Fields.SingleString;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    //    String address;
    //    String postCode;
    //    String date
    private FieldList fieldList;

    public long getId() {
        return id;
    }

    public FieldList getFieldList() {
        return fieldList;
    }

    public Date getDataOfUpload() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        SingleString singleString = (SingleString) fieldList.getFieldByName("date");
        return formatter.parse(singleString.getString());
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

    public PersonalInfo(){
    }
}
