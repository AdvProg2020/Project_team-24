package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.List;

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
    public List<Object> getParametersForPack() {
        return Arrays.asList(personalInformationId, firstName, lastName, email, phoneNumber);
    }

    public PersonalInformation(long personalInformationId, String firstName, String lastName, String email, String phoneNumber) {
        this.personalInformationId = personalInformationId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
