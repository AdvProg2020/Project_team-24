package Controller.Controllers;

import Controller.ControllerUnit;

import Model.Models.*;

import java.util.ArrayList;

public class GuestController{

    private ControllerUnit controllerUnit;
    //singleTone
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
    public Cart viewCart(){return null;}
    public ArrayList<Product> showProducts(){return null;}
    public String view(long productId){return null;}
    public void increase(long productId){}
    public void decrease(long productId){}
    public double showTotalPrice() {return 0;}
}