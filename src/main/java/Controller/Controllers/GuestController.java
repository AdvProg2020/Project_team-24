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
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;
    private  Guest guest = (Guest) controllerUnit.getAccount();
    /****************************************************singleTone***************************************************/
    private static GuestController guestController;

    private GuestController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public static GuestController getInstance(ControllerUnit controllerUnit) {
        if (guestController == null) {
            guestController = new GuestController(controllerUnit);
        }
        return guestController;
    }

    /**************************************************methods********************************************************/

}