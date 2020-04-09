package Modols.PersonalInformation;

public class PersonalInformation {

    protected int personalInformationId;
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String password;

    public int getPersonalInformationId() {
        return personalInformationId;
    }

    public PersonalInformation(int personalInformationId, String userName, String firstName, String lastName, String email, String phoneNumber, String password) {
        this.personalInformationId = personalInformationId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
