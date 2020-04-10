package Model.Models;

import java.io.File;

public class PersonalInformation {

    protected static int totalNumberOfPersonalInformationCreated;
    protected static File file;

    static {
        file = new File("");

    }

    protected long personalInformationId;
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String password;

    public long getPersonalInformationId() {
        return personalInformationId;
    }

    /*********************Serialize************************/



    /******************************************************/

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
