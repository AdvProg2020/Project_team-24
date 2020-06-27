package Graphics;

import Controller.Controllers.FilterController;
import Controller.Controllers.ProductsController;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

public class Sort {
    public FilterController filterController = FilterController.getInstance();
    public ProductsController productsController = ProductsController.getInstance();
    public CheckBox Point;
    public CheckBox TimeOfUpload;
    public CheckBox NumberOfViews;
    public ChoiceBox productInfo;
    public ChoiceBox CategoryInfo;


    public void Point() {
        productsController.sort("Time");
    }

    public void NumberOfViews() {
        productsController.sort("Time");
    }

    public void TimeOfUpload() {
        productsController.sort("Time");
    }
}
