package Graphics;

import Controller.Controllers.FilterController;
import Controller.Controllers.ProductsController;
import Exceptions.NotAvailableSortException;
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
        if(Point.isSelected()) {

            try {
                productsController.sort("Point");
                System.out.println("sort point");
            } catch (NotAvailableSortException e) {
                e.printStackTrace();
            }
            NumberOfViews.setDisable(true);
            TimeOfUpload.setDisable(true);
        }else{
            NumberOfViews.setDisable(false);
            TimeOfUpload.setDisable(false);

        }

    }

    public void NumberOfViews(ActionEvent event) {
        if(NumberOfViews.isSelected()) {
            try {
                productsController.sort("NumberOfVisits");
                System.out.println("sort number");
            } catch (NotAvailableSortException e) {
                e.printStackTrace();
            }
            Point.setDisable(true);
            TimeOfUpload.setDisable(true);
        }else{
            Point.setDisable(false);
            TimeOfUpload.setDisable(false);
        }

    }

    public void TimeOfUpload(ActionEvent event) {
        if(TimeOfUpload.isSelected()) {
            try {
                productsController.sort("Time");
                System.out.println("sort time");
            } catch (NotAvailableSortException e) {
                e.printStackTrace();
            }
            Point.setDisable(true);
            NumberOfViews.setDisable(true);
        }else{
            Point.setDisable(false);
            NumberOfViews.setDisable(false);
        }
    }
}
