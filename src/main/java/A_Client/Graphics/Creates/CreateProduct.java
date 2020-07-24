package A_Client.Graphics.Creates;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.FieldAndFieldList.Field;
import Structs.MiniCate;
import Structs.MiniProduct;
import Structs.ProductVsSeller.ProductOfSeller;
import com.gilecode.yagson.YaGson;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CreateProduct implements SceneBuilder, Initializable {

    private final Client client = SendAndReceive.getClient();
    private static Mode mode = Mode.New;
    private MiniProduct product;
    private File selectedImage;
    private File selectedMedia;
    private int category_f_index;
    private FileChooser fc = new FileChooser();
    private List<String> str_f_c = new ArrayList<>();
    private List<String> str_v_c = new ArrayList<>();
    private List<String> str_f_p = new ArrayList<>();
    private List<String> str_v_p = new ArrayList<>();
    private List<String> str_fcc = new ArrayList<>();
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

    public static void setMode(Mode mode) {
        CreateProduct.mode = mode;
    }

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
        product = SendAndReceive
                .getProductById(client.getClientInfo().getProductId());

        init_newMode();
        if (mode == Mode.Edit) init_editMode();
        if (mode == Mode.AddSeller) init_addSellerMode();
    }

    private void init_editMode() {
        product_name.setText(product.getProductName());
        long accountId = Long.parseLong(
                client.getClientInfo().getAccountId());

        ProductOfSeller productOfSeller = product.getProfSell().stream()
                .filter(p -> accountId == p.getSellerId()).findFirst()
                .orElseThrow(() -> new NullPointerException("Seller Not Found!"));

        product_price.setText(productOfSeller.getPrice() + "");
        product_number.setText(productOfSeller.getNumber() + "");

        SomeOfSetting();
    }

    private void SomeOfSetting() {

        if (product.getCateId() != null)
            category.setValue(SendAndReceive.getCateById(
                    product.getCateId()).getCateName());

        if (product.getAuctionId() != null)
            auction.setValue(SendAndReceive.getAuctionById(
                    product.getAuctionId()).getAuctionName());

        if (product.getMediasId() != null)
            product_image.setImage(SendAndReceive.getImageById(
                    product.getMediasId()));
    }

    private void init_addSellerMode() {
        MiniProduct product = SendAndReceive.getProductById(client.getClientInfo().getProductId());

        product_name.setText(product.getProductName());
        product_price.setPromptText("مقدار مورد نظر را وارد کنید");
        product_number.setPromptText("مقدار مورد نظر را وارد کنید");

        SomeOfSetting();

        product_name.setEditable(false);
        category.setDisable(true);
        auction.setDisable(true);
    }

    private void init_newMode() {
        category.setItems(
                FXCollections.observableArrayList(SendAndReceive.getAllCategories().stream()
                        .map(categoryPrime -> categoryPrime.getCateName() + " " + categoryPrime.getCateId())
                        .collect(Collectors.toList()))
        );
        category.getItems().add("Nothing");
        category.setValue("Nothing");

        auction.setItems(
                FXCollections.observableArrayList(SendAndReceive.getAllAuctions().stream()
                        .map(auctionPrime -> auctionPrime.getAuctionName() + " " + auctionPrime.getAuctionId())
                        .collect(Collectors.toList()))
        );
        auction.getItems().add("Nothing");
        auction.setValue("Nothing");
    }

    public void final_submit() {

        String productName = product_name.getText();
        String productPrice = product_price.getText();
        String productNumber = product_number.getText();

        reset();

        if (productName.isEmpty() || productPrice.isEmpty() || productNumber.isEmpty()) {
            mustBeFilled();
            return;
        }

        String productAction = auction.getValue().equals("Nothing") ? "0" : auction.getValue()
                .split(" ")[auction.getValue().split(" ").length - 1];
        String productCategory = category.getValue().equals("Nothing") ? "0" : category.getValue()
                .split(" ")[category.getValue().split(" ").length - 1];

        if (mode == Mode.New)
            submit_newMode(productName, productPrice, productNumber, productAction, productCategory);
        if (mode == Mode.Edit)
            submit_editMode(productName, productAction, productCategory);
        if (mode == Mode.AddSeller)
            submit_addSellerMode(productPrice, productNumber);
    }

    private void submit_addSellerMode(String productPrice, String productNumber) {
        SendAndReceive.addNewSellerOfPro(Arrays.asList(product.getProductId(), client.getClientInfo().getAccountId(), productPrice, productNumber));
        goMainMenu();
    }

    private void submit_editMode(String productName, String productAction, @NotNull String productCategory) {
        MiniProduct product = SendAndReceive.getProductById(client.getClientInfo().getProductId());
        sendRequestForEdit(product.getProductId(), "productName", productName, "edit product name");
        if (!productCategory.equals("0"))
            sendRequestForEdit(product.getProductId(), "category", productCategory, "edit product category");
        if (!productAction.equals("0"))
            sendRequestForEdit(product.getProductId(), "Auction", productAction, "edit product Auction");
        goMainMenu();
    }

    private void sendRequestForEdit(String productId, String fieldName, String value, String information) {
        SendAndReceive.EditProduct(Arrays.asList(productId, fieldName, value, information));
    }

    private void submit_newMode(String productName, String productPrice, String productNumber, String productAction, String productCategory) {

        SendAndReceive.addProduct(Arrays.asList(productName, productCategory, productAction, productNumber, productPrice));

        afterFirstSubmit();

        if (!productCategory.equals("0")) {

            category_table.setDisable(false);
            category_feature.setDisable(false);
            category_value.setDisable(false);
            s_category.setDisable(false);

            MiniCate miniCate = SendAndReceive.getCateById(productCategory);

            str_fcc.addAll(
                    miniCate.getFieldList()
                            .getFieldList()
                            .stream()
                            .map(Field::getFieldName)
                            .collect(Collectors.toList())
            );

            if (!str_fcc.isEmpty()) {
                category_feature.setText(str_fcc.get(0));
                str_f_c.add(str_fcc.get(0));
            }
        }

        f_submit.setOnAction(event -> {

            if (next_submit_newMode()) return;

            YaGson yaGson = new YaGson();
            SendAndReceive.saveInfoOfProduct(Arrays.asList(yaGson.toJson(str_f_p),
                    yaGson.toJson(str_v_p),yaGson.toJson(str_f_c), yaGson.toJson(str_v_c)));

            goMainMenu();
        });
    }

    private boolean next_submit_newMode() {
        reset();

        if (str_v_c.size() != str_fcc.size()) {
            mustFillCategoryValues();
            return false;
        }

        if (selectedImage != null || selectedMedia != null)
            SendAndReceive.setMedias(selectedImage, selectedMedia);

        return true;
    }

    private void mustFillCategoryValues() {
        category_value.setTooltip(getTooltip());
        category_value.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
    }

    private void reset() {
        product_name.setTooltip(null);
        product_name.setStyle("-fx-border-color: white;");
        product_number.setTooltip(null);
        product_number.setStyle("-fx-border-color: white;");
        product_price.setTooltip(null);
        product_price.setStyle("-fx-border-color: white;");
    }

    public void select_image() {
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("image", "*.jpg", "*.png"));
        selectedImage = fc.showOpenDialog(null);
        if (selectedImage == null) return;
        Image value = new Image(selectedImage.toURI().toString());
        product_image.setImage(value);
    }

    public void select_movie() {
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("movie", "*.mp4"));
        selectedMedia = fc.showOpenDialog(null);
    }

    public void product_submit() {
        String feature = product_feature.getText();
        String value = product_value.getText();
        product_feature.setText("");
        product_value.setText("");
        str_f_p.add(feature);
        str_v_p.add(value);

        setTable(product_table, feature_product_column, value_product_column, str_f_p, str_v_p);
    }

    public void category_submit() {
        String value = category_value.getText();
        category_value.setText("");
        str_v_c.add(value);

        setTable(category_table, feature_category_column, value_category_column, str_f_c, str_v_c);

        category_value.setTooltip(null);
        category_value.setStyle("-fx-border-color: white;");

        if (category_f_index >= str_fcc.size() - 1) {

            category_table.setDisable(true);
            category_feature.setDisable(true);
            category_value.setDisable(true);
            s_category.setDisable(true);
            return;
        }

        int index = ++category_f_index;
        category_feature.setText(str_fcc.get(index));
        str_f_c.add(str_fcc.get(index));
    }

    private void setTable(TableView<Field> table, TableColumn<Field, String> features, TableColumn<Field, String> values, @NotNull List<String> featureList, List<String> valueList) {
        List<Field> fieldList = new ArrayList<>();
        for (int i = 0; i < featureList.size(); i++) {
            fieldList.add(new Field(featureList.get(i), valueList.get(i)));
        }
        table.setItems(FXCollections.observableList(fieldList));
        features.setCellValueFactory(new PropertyValueFactory<>("fieldName"));
        values.setCellValueFactory(new PropertyValueFactory<>("string"));
    }

    private void goMainMenu() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    private void mustBeFilled() {
        Tooltip mustFilled = getTooltip();
        product_name.setTooltip(mustFilled);
        product_name.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        product_number.setTooltip(mustFilled);
        product_number.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        product_price.setTooltip(mustFilled);
        product_price.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        failSound();
    }

    @NotNull
    private Tooltip getTooltip() {
        Tooltip mustFilled = new Tooltip();
        mustFilled.setText("این فیلد را باید پر کنید");
        mustFilled.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        return mustFilled;
    }

    private void afterFirstSubmit() {
        s_image.setDisable(false);
        s_movie.setDisable(false);
        product_image.setDisable(false);
        product_image.setOpacity(1);
        s_product.setDisable(false);
        text_product.setDisable(false);
        text_category.setDisable(false);
        Icon_product.setDisable(false);
        Icon_product.setOpacity(1);
        product_feature.setDisable(false);
        product_value.setDisable(false);
        product_table.setDisable(false);
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
                )).play()
        ).start();
    }

    public enum Mode {
        Edit, New, AddSeller
    }
}
