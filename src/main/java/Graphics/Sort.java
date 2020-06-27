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


    public void Point(ActionEvent event) {
        productsController.sort("Point");

    }

    public void NumberOfViews(ActionEvent event) {
        productsController.sort("NumberOfVisits");
    }

    public void TimeOfUpload(ActionEvent event) {
        productsController.sort("Time");
    }
}
