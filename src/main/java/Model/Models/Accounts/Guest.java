package Model.Models.Accounts;

import Model.DataBase.DataBase;
import Model.Models.*;
import Model.Tools.Data;

public class Guest extends Account {

    public void createNewAccount(Account account) throws Exception {
        list.add(account);
        DataBase.save(account, true);
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Account> pack() {
        return super.pack().setInstance(new Guest());
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
