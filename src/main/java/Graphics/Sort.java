package Graphics;

import Controller.Controllers.FilterController;
import Controller.Controllers.ProductsController;
import Exceptions.NotAvailableSortException;
import Graphics.Tools.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

import java.io.File;
import java.io.IOException;

public class Sort implements SceneBuilder {

    public FilterController filterController = FilterController.getInstance();
    public ProductsController productsController = ProductsController.getInstance();
    public CheckBox Point;
    public CheckBox TimeOfUpload;
    public CheckBox NumberOfViews;
    public ChoiceBox<String> productInfo;
    public ChoiceBox<String> CategoryInfo;

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/MainMenu/MainMenu.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

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
