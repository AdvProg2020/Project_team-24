package B_Server.Model.Models.Accounts;

import B_Server.Model.Models.Account;
import B_Server.Model.Models.Data.Data;
import B_Server.Model.Models.Info;
import Exceptions.*;
import org.jetbrains.annotations.NotNull;

public class Supporter extends Account {

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Account> pack() {
        return super.pack().setInstance(new Supporter());
    }

    /**************************************************constructors*****************************************************/

    public Supporter(String username) {
        super(username);
    }

    private Supporter() {
    }
}
