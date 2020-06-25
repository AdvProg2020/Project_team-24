package Graphics.Creates;

import Graphics.Tools.SceneBuilder;
import Model.Models.Field.Field;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateProduct implements SceneBuilder, Initializable {

    public TextField product_name;
    public TextField product_price;
    public TextField product_number;
    public ImageView product_image;
    public TextField category_feature;
    public TextField category_value;
    public TextField product_feature;
    public TextField product_value;
    public TableView<Field> category_table;
    public TableView<Field> product_table;
    public ChoiceBox<String> auction;
    public ChoiceBox<String> category;

    @Override
    public Scene sceneBuilder() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
