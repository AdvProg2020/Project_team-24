package B_Server.Controller.Tools;

import B_Server.Controller.Controllers.*;
import B_Server.Controller.Controllers.AccountControllers.BuyerController;
import B_Server.Controller.Controllers.AccountControllers.ManagerController;
import B_Server.Controller.Controllers.AccountControllers.SellerController;
import B_Server.Controller.Controllers.AccountControllers.SupporterController;
import B_Server.Server.InstantInfo.InstantInfo;

import java.util.Arrays;
import java.util.List;

public class LocalClientInfo {

    protected ThreadLocal<InstantInfo> clientInfo = new ThreadLocal<>();

    public ThreadLocal<InstantInfo> getClientInfo() {
        return clientInfo;
    }

    public static void initControllers(InstantInfo info) {

        List<LocalClientInfo> localClientInfos = Arrays.asList(
                BuyerController.getInstance(),
                SupporterController.getInstance(),
                SellerController.getInstance(),
                ManagerController.getInstance(),
                AuctionController.getInstance(),
                FilterController.getInstance(),
                LoginController.getInstance(),
                ProductController.getInstance(),
                ProductsController.getInstance(),
                SignUpController.getInstance()
        );

        localClientInfos.forEach(localClientInfo -> localClientInfo.getClientInfo().set(info));
    }
}
