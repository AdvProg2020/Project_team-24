package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    public List<Object> getParametersForPack() {
        return Arrays.asList(companyId, name, phoneNumber, email, foundation);
    }

    public CompanyInformation(long companyId, String name, String phoneNumber, String email, Date foundation) {
        this.companyId = companyId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.foundation = foundation;
    }
}
