package Graphics;

import Controller.ControllerUnit;
import Controller.Controllers.FilterController;
import Controller.Controllers.ProductsController;
import Exceptions.InvalidFilterException;
import Exceptions.NotAvailableSortException;
import Model.Models.Category;
import Model.Models.Field.Field;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Sort implements Initializable {

    public FilterController filterController = FilterController.getInstance();
    public ProductsController productsController = ProductsController.getInstance();
    public CheckBox Point;
    public CheckBox TimeOfUpload;
    public CheckBox NumberOfViews;
    public ChoiceBox<String> productInfo;
    public ChoiceBox<String> CategoryInfo;
    public TextField product_f_value;
    public TextField category_f_value;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<String> infos = Arrays.asList("Nothing", "ProductName", "CategoryName");
        productInfo.getItems().setAll(infos);
        productInfo.setValue("Nothing");

        Category category = ControllerUnit.getInstance().getCategory();

        if (category != null) {

            infos = category.getCategoryFields()
                    .getFieldList()
                    .stream()
                    .map(Field::getFieldName)
                    .collect(Collectors.toList());


        } else infos = new ArrayList<>();

        infos.add("Nothing");

        CategoryInfo.getItems().setAll(infos);
        CategoryInfo.setValue("Nothing");
    }

    public void Point() {

        if (Point.isSelected()) {

            try {
                productsController.sort("Point");
            } catch (NotAvailableSortException e) {
                e.printStackTrace();
            }
            NumberOfViews.setDisable(true);
            TimeOfUpload.setDisable(true);
        } else {
            NumberOfViews.setDisable(false);
            TimeOfUpload.setDisable(false);

        }
    }

    public void NumberOfViews() {
        if (NumberOfViews.isSelected()) {
            try {
                productsController.sort("NumberOfVisits");
            } catch (NotAvailableSortException e) {
                e.printStackTrace();
            }
            Point.setDisable(true);
            TimeOfUpload.setDisable(true);
        } else {
            Point.setDisable(false);
            TimeOfUpload.setDisable(false);
        }
    }

    public void TimeOfUpload() {
        if (TimeOfUpload.isSelected()) {
            try {
                productsController.sort("Time");
            } catch (NotAvailableSortException e) {
                e.printStackTrace();
            }
            Point.setDisable(true);
            NumberOfViews.setDisable(true);
        } else {
            Point.setDisable(false);
            NumberOfViews.setDisable(false);
        }
    }

    public void product_f_enter(KeyEvent keyEvent) {
        String item = productInfo.getValue();
        if (!item.equals("Nothing") && keyEvent.getCode() == KeyCode.ENTER) {

            String value = product_f_value.getText();
            try {
                filterController.filter(item, value);
            } catch (InvalidFilterException e) {
                e.printStackTrace();
            }
        }
    }

    public void category_f_enter(KeyEvent keyEvent) {
        String item = productInfo.getValue();
        if (!item.equals("Nothing") && keyEvent.getCode() == KeyCode.ENTER) {

            String value = category_f_value.getText();
            try {
                filterController.filter(item, value);
            } catch (InvalidFilterException e) {
                e.printStackTrace();
            }
        }
    }
}
