package B_Server.Controller.Controllers;

public class GuestController {

    /******************************************************fields*******************************************************/

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