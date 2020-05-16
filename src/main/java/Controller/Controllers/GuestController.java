package Controller.Controllers;

import Controller.ControllerUnit;

public class GuestController {

    /******************************************************fields*******************************************************/

    private static ControllerUnit controllerUnit = ControllerUnit.getInstance();

    private static GuestController guestController = new GuestController();

    /******************************************************singleTone***************************************************/

    public static GuestController getInstance() {
        return guestController;
    }

    private GuestController() {
    }

    /****************************************************methods********************************************************/

    //?
}