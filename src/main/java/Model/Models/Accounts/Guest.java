package Model.Models.Accounts;

import Model.DataBase.DataBase;
import Model.Models.*;

public class Guest extends Account {

    public void createNewAccount(Account account) {
        list.add(account);
        DataBase.save(account);
    }

    /**************************************************constructors*****************************************************/

    public Guest(long id, String userName, String password, Info personalInfo) {
        super(id, userName, password, personalInfo);
    }

    public Guest() {
    }

    /****************************************************overrides******************************************************/

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
