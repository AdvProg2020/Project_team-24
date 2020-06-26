package Graphics.Creates;

import Controller.Controllers.SellerController;
import Exceptions.AuctionDoesNotExistException;
import Exceptions.CategoryDoesNotExistException;
import Graphics.Tools.SceneBuilder;
import Model.Models.*;
import Model.Models.Field.Field;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class CreateProduct implements SceneBuilder, Initializable {

    private static SellerController sellerController = SellerController.getInstance();
    private Product product;
    private List<String> str_f_c;
    private List<String> str_v_c;
    private List<String> str_f_p;
    private List<String> str_v_p;
    @FXML
    private Button f_submit;
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
    @FXML
    private Button s_image;
    @FXML
    private Button s_movie;
    @FXML
    private ImageView Icon_product;
    @FXML
    private Label text_category;
    @FXML
    private Label text_product;
    @FXML
    private Button s_product;
    @FXML
    private Button s_category;
    @FXML
    private Label text_cb_auction;
    @FXML
    private Label text_cb_category;
    @FXML
    private TableColumn<Field, String> value_category_column;
    @FXML
    private TableColumn<Field, String> feature_category_column;
    @FXML
    private TableColumn<Field, String> value_product_column;
    @FXML
    private TableColumn<Field, String> feature_product_column;

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
        category.setItems(
                FXCollections.observableArrayList(Category.getList().stream()
                        .map(categoryPrime -> categoryPrime.getName() + " " + categoryPrime.getId())
                        .collect(Collectors.toList()))
        );
        auction.setItems(
                FXCollections.observableArrayList(Auction.getList().stream()
                        .map(auctionPrime -> auctionPrime.getName() + " " + auctionPrime.getId())
                        .collect(Collectors.toList()))
        );
    }

    public void final_submit() {

        String productName = product_name.getText();
        String productPrice = product_price.getText();
        String productNumber = product_number.getText();

        if (productName.isEmpty() || productPrice.isEmpty() || productNumber.isEmpty()) {
            mustBeFilled();
            return;
        }

        String productAction = auction.getValue().isEmpty() ? "0" : auction.getValue()
                .split(" ")[auction.getValue().split(" ").length - 1];
        String productCategory = category.getValue().isEmpty() ? "0" : category.getValue()
                .split(" ")[category.getValue().split(" ").length - 1];

        try {
            product = sellerController.createTheBaseOfProduct(productName, productCategory, productAction, productNumber, productPrice);
            afterFirstSubmit();

            f_submit.setOnAction(event -> {

                sellerController.saveProductInfo(product, str_f_p, str_v_p);
                sellerController.saveCategoryInfo(product, str_f_c, str_v_c);
                sellerController.sendRequest(product, "new Product", "new");
            });

        } catch (AuctionDoesNotExistException | CategoryDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public void select_image() {
    }

    public void select_movie() {
    }

    public void product_submit() {
        String feature = product_feature.getText();
        String value = product_value.getText();

        str_f_p.add(feature);
        str_v_p.add(value);

        setTable(product_table, feature_product_column, value_product_column, str_f_p, str_v_p);
    }

    public void category_submit() {
        String feature = category_feature.getText();
        String value = category_value.getText();

        str_f_c.add(feature);
        str_v_c.add(value);

        setTable(product_table, feature_category_column, value_category_column, str_f_c, str_v_c);
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

    private void mustBeFilled() {
        Tooltip mustFilled = new Tooltip();
        mustFilled.setText("این فیلد را باید پر کنید");
        mustFilled.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        product_name.setTooltip(mustFilled);
        product_name.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        product_number.setTooltip(mustFilled);
        product_number.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        product_price.setTooltip(mustFilled);
        product_price.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        failSound();
    }

    private void afterFirstSubmit() {
        s_image.setDisable(false);
        s_movie.setDisable(false);
        product_image.setVisible(false);
        product_image.setOpacity(1);
        s_category.setDisable(false);
        s_product.setDisable(false);
        text_product.setDisable(false);
        text_category.setDisable(false);
        Icon_product.setDisable(false);
        Icon_product.setOpacity(1);
        product_feature.setDisable(false);
        category_feature.setDisable(false);
        product_value.setDisable(false);
        category_value.setDisable(false);
        product_table.setDisable(false);
        category_table.setDisable(false);
        product_name.setDisable(true);
        product_number.setDisable(true);
        product_price.setDisable(true);
        text_cb_auction.setDisable(true);
        text_cb_category.setDisable(true);
        category.setDisable(true);
        auction.setDisable(true);
    }

    private void failSound() {
        new Thread(() -> new MediaPlayer(
                new Media(
                        new File("src/main/resources/Graphics/SoundEffect/failSound.mp3").toURI().toString()
                )).play()).start();
    }
}
