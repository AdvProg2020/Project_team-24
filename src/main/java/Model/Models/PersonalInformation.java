package Model.Models;

public class PersonalInformation {

    public long personalInformationId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String factoryName;

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

    public String getFactoryName() {
        return factoryName;
    }

    public PersonalInformation(long personalInformationId, String firstName, String lastName, String email, String phoneNumber, String factoryName) {
        this.personalInformationId = personalInformationId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.factoryName = factoryName;
    }
}
