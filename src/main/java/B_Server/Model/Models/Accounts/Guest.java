package B_Server.Model.Models.Accounts;

import B_Server.Model.Models.Account;
import B_Server.Model.Models.Info;
import B_Server.Model.Tools.AddingNew;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class Guest extends Account {

    /***************************************************otherMethods****************************************************/

    @NotNull
    public static Guest autoCreateGuest() {
        synchronized (staticLock) {
            long id = AddingNew.getRegisteringId().apply(getList());
            return new Guest(id, "guest " + id, "", new Info("guestInfo", null, LocalDate.now()));
        }
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
