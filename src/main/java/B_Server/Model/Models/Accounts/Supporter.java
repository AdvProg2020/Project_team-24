package B_Server.Model.Models.Accounts;

import B_Server.Model.Models.Account;
import B_Server.Model.Models.Info;

public class Supporter extends Account {
    public Supporter(long id, String userName, String password, Info personalInfo) {
        super(id, userName, password, personalInfo);
    }

    public void createGroupChat(){
        //...
    }
}
