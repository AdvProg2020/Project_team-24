package Graphics.Creates;

import Graphics.Tools.SceneBuilder;
import Model.Models.Field.Field;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateProduct implements SceneBuilder, Initializable {

    @FXML
    private TextField product_name;
    @FXML
    private TextField product_price;
    @FXML
    private TextField product_number;
    @FXML
    private ImageView product_image;
    @FXML
    private TextField category_feature;
    @FXML
    private TextField category_value;
    @FXML
    private TextField product_feature;
    @FXML
    private TextField product_value;
    @FXML
    private TableView<Field> category_table;
    @FXML
    private TableView<Field> product_table;
    @FXML
    private ChoiceBox<String> auction;
    @FXML
    private ChoiceBox<String> category;

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/CreatePages/CreateProduct/CreateProduct.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        category.
    }

    public void final_submit() {
    }

    public void select_image() {
    }

    public void select_movie() {
    }

    public void product_submit() {
    }

    public void category_submit() {
    }
}
