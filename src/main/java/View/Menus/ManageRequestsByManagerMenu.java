package View.Menus;

import Controller.Controllers.ManagerController;
import Exceptions.RequesDoesNotExistException;

import java.util.List;
import java.util.Optional;

public class ManageRequestsByManagerMenu extends Menu {

    private static ManageRequestsByManagerMenu menu;
    private static ManagerController managerController = ManagerController.getInstance();

    public ManageRequestsByManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManageRequestsByManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageRequestsByManagerMenu(name, parent);
        }
        return menu;
    }

    public void showDetails(List<String> inputs) {
       String id = inputs.get(0);
        try {
            managerController.detailsOfRequest(id);
        } catch (RequesDoesNotExistException e) {
            System.out.println("this request with this id does not exist");
        }
    }

    public void acceptRequest(List<String> inputs) {
        String id = inputs.get(0);
        try {
            managerController.acceptRequest(id);
        } catch (Exception e) {
            //yac
        }
    }

    public void declineRequest(List<String> inputs) {
        String id = inputs.get(0);
        try {
            managerController.denyRequest(id);
        } catch (Exception e) {
            //yac
        }
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println("You're in ManageRequestsByManagerMenu");
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
