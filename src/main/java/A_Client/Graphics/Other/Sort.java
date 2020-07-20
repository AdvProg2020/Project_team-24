package A_Client.Graphics.Other;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Menus.ProductsMenu;
import A_Client.Graphics.Models.ProductCart;
import Structs.MiniCate;
import Structs.MiniProduct;
import javafx.fxml.FXML;
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

    private final Client client = SendAndReceive.getClient();
    @FXML
    private CheckBox Point;
    @FXML
    private CheckBox TimeOfUpload;
    @FXML
    private CheckBox NumberOfViews;
    @FXML
    private ChoiceBox<String> productInfo;
    @FXML
    private ChoiceBox<String> CategoryInfo;
    @FXML
    private TextField product_f_value;
    @FXML
    private TextField category_f_value;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<String> infos = Arrays.asList("Nothing", "ProductName", "CategoryName");
        productInfo.getItems().setAll(infos);
        productInfo.setValue("Nothing");

        MiniCate category = SendAndReceive.getCateById(client.getClientInfo().getCateId());

        if (category != null) {

            infos = category.getFieldList()
                    .getList()
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

            SendAndReceive.ProductSort("Point");
            NumberOfViews.setDisable(true);
            TimeOfUpload.setDisable(true);
        } else {
            NumberOfViews.setDisable(false);
            TimeOfUpload.setDisable(false);
        }
    }

    public void NumberOfViews() {
        if (NumberOfViews.isSelected()) {

            SendAndReceive.ProductSort("NumberOfVisits");
            Point.setDisable(true);
            TimeOfUpload.setDisable(true);
        } else {
            Point.setDisable(false);
            TimeOfUpload.setDisable(false);
        }
    }

    public void TimeOfUpload() {
        if (TimeOfUpload.isSelected()) {

            SendAndReceive.ProductSort("Time");
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
            SendAndReceive.addNewFilter(Arrays.asList(item, value));
            setLists();
        }
    }

    public void category_f_enter(KeyEvent keyEvent) {
        String item = productInfo.getValue();
        if (!item.equals("Nothing") && keyEvent.getCode() == KeyCode.ENTER) {
            String value = category_f_value.getText();
            SendAndReceive.addNewFilter(Arrays.asList(item, value));
            setLists();
        }
    }

    private void setLists() {
        List<MiniProduct> list = SendAndReceive.getAllProducts();
        setProducts(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    private void setProducts(List<MiniProduct> list) {
        ProductsMenu.setList(list);
        ProductCart.setProductList(list);
    }
}
