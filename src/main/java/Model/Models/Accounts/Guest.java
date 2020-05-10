package Model.Models.Accounts;

import Model.DataBase.DataBase;
import Model.Models.*;
import Model.Models.Info.PersonalInfo;

public class Guest extends Account {

    public void createNewAccount(Account account) {
        list.add(account);
        DataBase.save(account);
    }

    public Guest(long id, String userName, String password, PersonalInfo personalInfo) {
        super(id, userName, password, personalInfo);
    }

    public Guest() {
    }

    @Override
    public String toString() {
        return "Guest{" +
                ", id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", personalInfo=" + personalInfo +
                '}';
    }
}
