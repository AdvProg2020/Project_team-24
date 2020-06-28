package Graphics.Creates;

import Controller.ControllerUnit;
import Controller.Controllers.SellerController;
import Exceptions.*;
import Graphics.MainMenu;
import Graphics.Tools.SceneBuilder;
import Model.DataBase.DataBase;
import Model.Models.*;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Field;
import Model.Models.Structs.Medias;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

public class CreateProduct implements SceneBuilder, Initializable {

    private static SellerController sellerController = SellerController.getInstance();
    private static Mode mode = Mode.New;
    private Product product;
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
        product = ControllerUnit.getInstance().getProduct();
        init_newMode();
        if (mode == Mode.Edit) init_editMode();
        if (mode == Mode.AddSeller) init_addSellerMode();
    }

    private void init_editMode() {
        Product product = ControllerUnit.getInstance().getProduct();
        Seller seller = (Seller) ControllerUnit.getInstance().getAccount();
        product_name.setText(product.getName());
        try {
            product_price.setText(product.getProductOfSellerById(seller.getId()).getPrice() + "");
            product_number.setText(product.getProductOfSellerById(seller.getId()).getNumber() + "");
        } catch (SellerDoesNotSellOfThisProduct sellerDoesNotSellOfThisProduct) {
            sellerDoesNotSellOfThisProduct.printStackTrace();
        }
        if (product.getCategory() != null)
            category.setValue(product.getCategory().getName());
        if (product.getAuction() != null)
            auction.setValue(product.getAuction().getName());
        if (product.getMediaId() != 0) {
            try {
                product_image.setImage(Medias.getImage(Medias.getMediasById(product.getMediaId()).getImageSrc()));
            } catch (ProductMediaNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void init_addSellerMode() {
        Product product = ControllerUnit.getInstance().getProduct();
        product_name.setText(product.getName());
        product_price.setPromptText("مقدار مورد نظر را وارد کنید");
        product_number.setPromptText("مقدار مورد نظر را وارد کنید");
        if (product.getCategory() != null)
            category.setValue(product.getCategory().getName());
        if (product.getAuction() != null)
            auction.setValue(product.getAuction().getName());
        if (product.getMediaId() != 0) {
            try {
                product_image.setImage(Medias.getImage(Medias.getMediasById(product.getMediaId()).getImageSrc()));
            } catch (ProductMediaNotFoundException e) {
                e.printStackTrace();
            }
        }
        product_name.setEditable(false);
        category.setDisable(true);
        auction.setDisable(true);
    }

    private void init_newMode() {
        category.setItems(
                FXCollections.observableArrayList(Category.getList().stream()
                        .map(categoryPrime -> categoryPrime.getName() + " " + categoryPrime.getId())
                        .collect(Collectors.toList()))
        );
        category.getItems().add("Nothing");
        category.setValue("Nothing");

        auction.setItems(
                FXCollections.observableArrayList(Auction.getList().stream()
                        .map(auctionPrime -> auctionPrime.getName() + " " + auctionPrime.getId())
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

        try {

            if (mode == Mode.New)
                submit_newMode(productName, productPrice, productNumber, productAction, productCategory);
            if (mode == Mode.Edit)
                submit_editMode(productName, productAction, productCategory);
            if (mode == Mode.AddSeller)
                submit_addSellerMode(productPrice, productNumber);

        } catch (AuctionDoesNotExistException | CategoryDoesNotExistException | ProductDoesNotExistException | FieldDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    private void submit_addSellerMode(String productPrice, String productNumber) {
        Account account = ControllerUnit.getInstance().getAccount();
        product.addSeller(account.getId(), Double.parseDouble(productPrice), Long.parseLong(productNumber));
        ((Seller) account).addToProductList(product.getId());
        DataBase.save(product);
        goMainMenu();
    }

    private void submit_editMode(String productName, String productAction, @NotNull String productCategory) throws AuctionDoesNotExistException, FieldDoesNotExistException, CategoryDoesNotExistException, ProductDoesNotExistException {
        Product product = ControllerUnit.getInstance().getProduct();
        sellerController.editProduct(product.getId() + "", "productName", productName, "edit product name");
        if (!productCategory.equals("0"))
            sellerController.editProduct(product.getId() + "", "category", productCategory, "edit product name");
        if (!productAction.equals("0"))
            sellerController.editProduct(product.getId() + "", "Auction", productAction, "edit product name");
        goMainMenu();
    }

    private void submit_newMode(String productName, String productPrice, String productNumber, String productAction, String productCategory) throws AuctionDoesNotExistException, CategoryDoesNotExistException {
        product = sellerController.createTheBaseOfProduct(productName, productCategory, productAction, productNumber, productPrice);
        afterFirstSubmit();

        Category category = product.getCategory();

        if (category != null) {

            category_table.setDisable(false);
            category_feature.setDisable(false);
            category_value.setDisable(false);
            s_category.setDisable(false);

            str_fcc.addAll(
                    category.getCategoryFields()
                            .getFieldList()
                            .stream()
                            .map(Field::getFieldName)
                            .collect(Collectors.toList())
            );

            category_feature.setText(str_fcc.get(0));
            str_f_c.add(str_fcc.get(0));
        }

        f_submit.setOnAction(event -> {

            if (next_submit_newMode()) return;

            sellerController.saveProductInfo(product, str_f_p, str_v_p);
            sellerController.saveCategoryInfo(product, str_f_c, str_v_c);
            sellerController.sendRequest(product, "new Product", "new");
            goMainMenu();
        });
    }

    private boolean next_submit_newMode() {
        reset();

        if (str_v_c.size() != str_fcc.size()) {
            mustFillCategoryValues();
            return true;
        }

        if (selectedImage != null || selectedMedia != null) {

            Medias medias = new Medias();
            Medias.addMedia(medias);
            product.setMediaId(medias.getId());

            if (selectedImage != null) {
                setImage();
                medias.setImageSrc(selectedImage.toURI().toString());
            }
            if (selectedMedia != null) {
                setMedia();
                medias.setPlayerSrc(selectedMedia.toURI().toString());
            }
            DataBase.save(medias);
        }
        return false;
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

    private void setImage() {
        try {
            String[] str = selectedImage.getName().split("\\.");
            String first = "src/main/resources/DataBase/Images/" + product.getMediaId() + "." + str[str.length - 1];
            Files.copy(
                    selectedImage.toPath(),
                    Paths.get(first),
                    StandardCopyOption.REPLACE_EXISTING
            );
            selectedImage = new File(first);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setMedia() {
        try {
            String first = "src/main/resources/DataBase/Images/" + product.getMediaId() + ".mp4";
            Files.copy(
                    selectedMedia.toPath(),
                    Paths.get(first),
                    StandardCopyOption.REPLACE_EXISTING
            );
            selectedMedia = new File(first);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                )).play()).start();
    }

    public enum Mode {
        Edit, New, AddSeller
    }
}
