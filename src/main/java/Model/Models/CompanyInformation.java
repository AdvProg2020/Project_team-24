package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.Date;

public class CompanyInformation implements Packable {

    public long companyId;
    private String name;
    private String phoneNumber;
    private String email;
    private Date foundation;

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Date getFoundation() {
        return foundation;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public CompanyInformation(long companyId, String name, String phoneNumber, String email, Date foundation) {
        this.companyId = companyId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.foundation = foundation;
    }
}
