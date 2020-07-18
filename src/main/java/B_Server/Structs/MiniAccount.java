package B_Server.Structs;

import B_Server.Model.Models.FieldList;
import B_Server.Model.Models.Wallet;

public class MiniAccount {

    private final String mediasId;
    private final String username;
    private final String password;
    private final FieldList personalInfo;
    private final FieldList companyInfo;
    private final Wallet wallet;

    public String getMediasId() {
        return mediasId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public FieldList getCompanyInfo() {
        return companyInfo;
    }

    public FieldList getPersonalInfo() {
        return personalInfo;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public MiniAccount(String mediasId, String username, String password, FieldList personalInfo, FieldList companyInfo, Wallet wallet) {
        this.mediasId = mediasId;
        this.username = username;
        this.password = password;
        this.personalInfo = personalInfo;
        this.companyInfo = companyInfo;
        this.wallet = wallet;
    }
}
