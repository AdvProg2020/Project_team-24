package Model.Models;

public class PersonalInformation {

    public long personalInformationId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public long getPersonalInformationId() {
        return personalInformationId;
    }

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

    public PersonalInformation(long personalInformationId, String firstName, String lastName, String email, String phoneNumber) {
        this.personalInformationId = personalInformationId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
