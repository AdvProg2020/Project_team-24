package Controller.Controllers;

import Controller.ControllerUnit;

import Exceptions.NotEnoughCreditException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Guest;

import java.util.ArrayList;
import java.util.List;

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