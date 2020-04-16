package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

public class PersonalInformation implements Packable {

    public long personalInformationId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public PersonalInformation(long personalInformationId, String firstName, String lastName, String email, String phoneNumber) {
        this.personalInformationId = personalInformationId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
