package Graphics.Creates;

import Controller.Controllers.SellerController;
import Exceptions.ProductDoesNotExistException;
import Graphics.Tools.SceneBuilder;
import Model.Models.Category;
import Model.Models.Field.Field;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CreateCategory implements SceneBuilder, Initializable {

    private static SellerController sellerController = SellerController.getInstance();
    private List<String> str_feature = new ArrayList<>();
    private List<String> str_value = new ArrayList<>();
    @FXML
    private MenuButton selected_subCategory;
    @FXML
    private TextField feature;
    @FXML
    private TextField value;
    @FXML
    private TextField Category_name;
    @FXML
    private TableView<Field> table;
    @FXML
    private TableColumn<Field, String> value_column;
    @FXML
    private TableColumn<Field, String> feature_column;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/CreatePages/CreateCategory/CreateCategory.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<CheckMenuItem> checkMenuItems = sellerController.showProducts().stream()
                    .map(product -> product.getName() + " " + product.getId())
                    .map(CheckMenuItem::new).collect(Collectors.toList());

            selected_subCategory.getItems().addAll(checkMenuItems);
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public void submit() {
    }

    public void addField() {
        String feature = this.feature.getText();
        String value = this.value.getText();

        str_feature.add(feature);
        str_value.add(value);

        setTable(table, feature_column, value_column, str_feature, str_value);
    }

    private void setTable(TableView<Field> table, TableColumn<Field, String> features, TableColumn<Field, String> values, List<String> featureList, List<String> valueList) {
        List<Field> fieldList = new ArrayList<>();
        for (int i = 0; i < featureList.size(); i++) {
            fieldList.add(new Field(featureList.get(i), valueList.get(i)));
        }
        table.setItems(FXCollections.observableList(fieldList));
        features.setCellValueFactory(new PropertyValueFactory<>("fieldName"));
        values.setCellValueFactory(new PropertyValueFactory<>("string"));
    }
}
