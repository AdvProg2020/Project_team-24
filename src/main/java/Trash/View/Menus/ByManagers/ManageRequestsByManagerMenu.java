package Trash.View.Menus.ByManagers;

import B_Server.Controller.Controllers.AccountControllers.ManagerController;
import Exceptions.AccountDoesNotExistException;
import Exceptions.RequestDoesNotExistException;
import B_Server.Model.Models.Request;
import Trash.View.Menus.Menu;
import Trash.View.Tools.Shows;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class ManageRequestsByManagerMenu extends Menu {

    private static ManageRequestsByManagerMenu menu;

    private static ManagerController managerController = ManagerController.getInstance();

    public ManageRequestsByManagerMenu(String name) {
        super(name);
    }

    public static ManageRequestsByManagerMenu getInstance(String name) {
        if (menu == null) {
            menu = new ManageRequestsByManagerMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in ManageRequestsByManagerMenu."));
    }

    public void showDetails(@NotNull List<String> inputs) {
       String id = inputs.get(0);
        try {
            Request request = managerController.detailsOfRequest(id);
            System.out.println(
                    Shows.getShowRequest().apply(request)
            );

        } catch (RequestDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void acceptRequest(@NotNull List<String> inputs) {
        String id = inputs.get(0);
        try {
            managerController.acceptRequest(id);
            System.out.println("Request accepted.");
        } catch (RequestDoesNotExistException | AccountDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void declineRequest(@NotNull List<String> inputs) {
        String id = inputs.get(0);
        try {
            managerController.denyRequest(id);
            System.out.println("Request declined.");
        } catch (RequestDoesNotExistException | AccountDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void show() {
        System.out.println("You're in ManageRequestsByManagerMenu.");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "showDetails [requestId]: To show details" + System.lineSeparator() +
                "acceptRequest [requestId]: To accept request" + System.lineSeparator() +
                "declineRequest [requestId]: To decline request" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
