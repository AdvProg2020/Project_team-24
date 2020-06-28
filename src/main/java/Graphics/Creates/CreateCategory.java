package Graphics.Creates;

import Controller.ControllerUnit;
import Controller.Controllers.ManagerController;
import Controller.Controllers.SellerController;
import Exceptions.CategoryDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Graphics.MainMenu;
import Graphics.Tools.SceneBuilder;
import Model.DataBase.DataBase;
import Model.Models.Category;
import Model.Models.Field.Field;
import Model.Models.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CreateCategory implements SceneBuilder, Initializable {

    private static SellerController sellerController = SellerController.getInstance();
    private static ManagerController managerController = ManagerController.getInstance();
    private static Mode mode = Mode.New;
    private List<String> str_feature = new ArrayList<>();
    @FXML
    private MenuButton selected_subCategory;
    @FXML
    private TextField feature;
    @FXML
    private TextField Category_name;
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> feature_column;

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

        List<CheckMenuItem> checkMenuItems = sellerController.showCategories().stream()
                .map(product -> product.getName() + " " + product.getId())
                .map(CheckMenuItem::new).collect(Collectors.toList());

        selected_subCategory.getItems().addAll(checkMenuItems);

        if (mode == Mode.Edit) {
            init_editMode();
        }
    }

    private void init_editMode() {
        Category category = ControllerUnit.getInstance().getCategory();
        Category_name.setText(category.getName());
        List<String> collect = category.getCategoryFields()
                .getFieldList().stream()
                .map(Field::getFieldName)
                .collect(Collectors.toList());
        setTable(table, feature_column, collect);
    }

    public void submit() {
        String category_name = Category_name.getText();

        if (category_name.isEmpty()) {
            mustBeFilled();
            return;
        }

        List<String> ids = selected_subCategory.getItems().stream()
                .filter(menuItem -> ((CheckMenuItem) menuItem).isSelected())
                .map(menuItem -> {

                    String[] units = menuItem.getText().split(" ");
                    return units[units.length - 1];

                }).collect(Collectors.toList());

        try {
            Category category = managerController.createEmptyCategory(category_name, str_feature, ids);
            if (mode == Mode.Edit) {
                Category categoryFirst = ControllerUnit.getInstance().getCategory();
                categoryFirst.getProductList().forEach(aLong -> {
                    category.addToProductList(aLong);
                    try {
                        Product productById = Product.getProductById(aLong);
                        productById.setCategory(category);
                        DataBase.save(productById);
                    } catch (ProductDoesNotExistException e) {
                        e.printStackTrace();
                    }
                });
                Category.removeCategory(category);
                DataBase.save(category);
            }
        } catch (CategoryDoesNotExistException e) {
            e.printStackTrace();
        }

        goMainMenu();
    }

    private void goMainMenu() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    public void addField() {
        String feature = this.feature.getText();
        this.feature.setText("");
        str_feature.add(feature);
        setTable(table, feature_column, str_feature);
    }

    private void mustBeFilled() {
        Tooltip mustFilled = new Tooltip();
        mustFilled.setText("این فیلد را باید پر کنید");
        mustFilled.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        Category_name.setTooltip(mustFilled);
        Category_name.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        failSound();
    }

    private void setTable(TableView<String> table, TableColumn<String, String> features, List<String> featureList) {
        table.setItems(FXCollections.observableList(featureList));
        features.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
    }

    private void failSound() {
        new Thread(() -> new MediaPlayer(
                new Media(
                        new File("src/main/resources/Graphics/SoundEffect/failSound.mp3").toURI().toString()
                )).play()).start();
    }

    public enum Mode {
        Edit, New
    }
}
