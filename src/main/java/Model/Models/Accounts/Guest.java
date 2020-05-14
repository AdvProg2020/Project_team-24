package Model.Models.Accounts;

import Model.Models.*;
import Model.Tools.AddingNew;
import Model.Tools.Data;

import java.time.LocalDate;

public class Guest extends Account {

    /***************************************************otherMethods****************************************************/

    public static Guest autoCreateGuest() {
        long id = AddingNew.getRegisteringId().apply(getList());
        return new Guest(id,"guest " + id, "", new Info("guestInfo", null, LocalDate.now()));
    }

    /**************************************************constructors*****************************************************/

    private Guest(long id, String userName, String password, Info personalInfo) {
        super(id, userName, password, personalInfo);
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
