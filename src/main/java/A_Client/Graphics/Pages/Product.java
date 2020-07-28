package A_Client.Graphics.Pages;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Creates.CreatOffer;
import A_Client.Graphics.Creates.CreateProduct;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Menus.ProductsMenu;
import A_Client.Graphics.Models.CommentCart;
import A_Client.Graphics.Models.ProductCart;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.FieldAndFieldList.Field;
import Structs.MiniAccount;
import Structs.MiniComment;
import Structs.MiniProduct;
import Structs.ProductVsSeller.ProductOfSeller;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Product implements Initializable, SceneBuilder {

    private static MiniProduct First_Compare;
    private static MiniProduct productObject;
    private final Client client = SendAndReceive.getClient();
    private List<ImageView> stars = new ArrayList<>();
    private boolean bought = false;
    private int sellerIndex = 0;
    @FXML
    private Button createOffer;
    @FXML
    private Button download;
    @FXML
    private Button setPoint_btn;
    @FXML
    private TextField pointArea;
    @FXML
    private Button submit_comment;
    @FXML
    private TextField title;
    @FXML
    private TextField sender;
    @FXML
    private TextArea content;
    @FXML
    private ImageView product_image;
    @FXML
    private Label product_price;
    @FXML
    private ImageView star_01;
    @FXML
    private ImageView star_02;
    @FXML
    private ImageView star_03;
    @FXML
    private ImageView star_04;
    @FXML
    private ImageView star_05;
    @FXML
    private MediaView product_media;
    @FXML
    private MediaView gif;
    @FXML
    private Label productName;
    @FXML
    private Button edit_btn;
    @FXML
    private TableView<Field> categoryFeature;
    @FXML
    private TableView<Field> ProductFeatures;
    @FXML
    private TableColumn<Field, String> featureC;
    @FXML
    private TableColumn<Field, String> valueC;
    @FXML
    private TableColumn<Field, String> featureP;
    @FXML
    private TableColumn<Field, String> valueP;
    @FXML
    private MediaView leftGif;
    @FXML
    private Button addMe_btn;
    @FXML
    private Button deleteProduct_btn;
    @FXML
    private Button addToCart_btn;

    public static MiniProduct getFirst_Compare() {
        return First_Compare;
    }

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/Product/Product.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        stars.addAll(Arrays.asList(star_01, star_02, star_03, star_04, star_05));
        productObject = SendAndReceive.getProductById(client.getClientInfo().getProductId());

        if (productObject == null) return;

        if (!productObject.getMediasId().equals("0")) {
            Media media = SendAndReceive
                    .getMediaById(productObject.getMediasId());
            Image image = SendAndReceive
                    .getImageById(productObject.getMediasId());

            if (image != null)
                product_image.setImage(image);
            if (media != null)
                product_media.setMediaPlayer(new MediaPlayer(media));
        }
        setStars();
        setPrice();
        setName();

        String accountId = client.getClientInfo().getAccountId();

        if (!accountId.equals("0")) {

            MiniAccount account = SendAndReceive.getAccountById(accountId);

            if (account == null) return;

            bought = productObject.getBuyers().contains(Long.parseLong(account.getAccountId()));

            if (account.getAccountT().equals("Seller")) {

                boolean anyMatch = SendAndReceive.getAllMyProducts().stream()
                        .anyMatch(miniProduct -> miniProduct.getProductId().equals(productObject.getProductId()));

                if (anyMatch) {
                    edit_btn.setDisable(false);
                    deleteProduct_btn.setDisable(false);
                    createOffer.setDisable(false);
                } else addMe_btn.setDisable(false);
            }

            if (account.getAccountT().equals("Customer")) {
                addToCart_btn.setDisable(false);
                sender.setText(account.getUsername());
                if (bought) {
                    pointArea.setDisable(false);
                    setPoint_btn.setDisable(false);
                    download.setDisable(false);
                }
            }
        }

        MediaPlayer value = new MediaPlayer(
                new Media(
                        new File("src\\main\\resources\\Graphics\\Product\\addToCart.mp4").toURI().toString()));
        gif.setMediaPlayer(value);
        value.setCycleCount(Integer.MAX_VALUE);
        value.play();

        MediaPlayer v = new MediaPlayer(
                new Media(
                        new File("src\\main\\resources\\Graphics\\Product\\productPage.mp4").toURI().toString()));
        leftGif.setMediaPlayer(v);
        v.setCycleCount(Integer.MAX_VALUE);
        v.play();

        setCategoryFeature();
        setProductFeatures();
    }

    private void setName() {
        productName.setText(productObject.getProductName());
    }

    public void play() {
        product_media.getMediaPlayer().play();
    }

    public void compare() {
        if (getFirst_Compare() == null) {
            setFirst_Compare(productObject);
            goProductsArea();
        } else goComparingArea();
    }

    public void addToCart() {
        String accountTy = client.getClientInfo().getAccountTy();
        if (accountTy.equals("Customer")) {
            ProductOfSeller productOfSeller = productObject
                    .getProfSell().get(sellerIndex);

            SendAndReceive.addProductToCart(productObject.getProductId(),
                    productOfSeller.getSellerId() + "");
            show();
        }
    }

    private void show() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ثبت سفارش");
        alert.setContentText("سفارش شما با موفقیت ثبت شد");
        alert.showAndWait();
    }

    public static void setFirst_Compare(MiniProduct first_Compare) {
        First_Compare = first_Compare;
    }

    private void setPrice() {
        product_price.setText(productObject.getProfSell().get(sellerIndex).getPrice() + "");
    }

    private void setStars() {
        Image filled = new Image(
                new File("src/main/resources/Graphics/Product/icons8-star-filled-16.png").toURI().toString());
        for (int i = 0; i < Double.parseDouble(productObject.getAveRate()); i++) {
            stars.get(i).setImage(filled);
        }
    }

    private void goProductsArea() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        setProducts(SendAndReceive.getAllProductsPrime());
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    private void setProducts(List<MiniProduct> list) {
        ProductsMenu.setList(list);
        ProductCart.setProductList(list);
    }

    private void goComparingArea() {
        // ?
    }

    public void AddMeAsSeller() {
        CreateProduct.setMode(CreateProduct.Mode.AddSeller);
        MainMenu.change(new CreateProduct().sceneBuilder());
    }

    public void deleteProduct() {
        SendAndReceive.DeleteProductById(productObject.getProductId(),
                client.getClientInfo().getAccountId());
    }

    public void setCategoryFeature() {
        List<Field> list = SendAndReceive.getCateInfoOdProduct(productObject.getProductId());
        categoryFeature.setItems(FXCollections.observableList(list));
        featureC.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFieldName()));
        valueC.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getString()));
    }

    public void setProductFeatures() {
        List<Field> list = SendAndReceive.getProductInfoById(productObject.getProductId());
        ProductFeatures.setItems(FXCollections.observableList(list));
        featureP.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFieldName()));
        valueP.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getString()));
    }

    public void editProduct() {
        CreateProduct.setMode(CreateProduct.Mode.Edit);
        MainMenu.change(new CreateProduct().sceneBuilder());
    }

    public void submit_comment_btn() {
        reset_btn();
        if (client.getClientInfo().getAccountTy().equals("Customer")) {
            List<String> list = Arrays.asList(client.getClientInfo().getAccountId(),
                    client.getClientInfo().getProductId(), title.getText(), content.getText());

            SendAndReceive.addCommentToProduct(list);
            MainMenu.change(new Product().sceneBuilder());
        } else commentError();
        setCommentListToShow();
        MainMenu.change(new Product().sceneBuilder());
    }

    private void setCommentListToShow() {
        List<MiniComment> commentList = SendAndReceive
                .getAllCommentOfProduct(productObject.getProductId());

        commentList.sort(Collections.reverseOrder());
        CommentCart.setCommentList(commentList);
    }

    private void reset_btn() {
        submit_comment.setStyle("-fx-border-color: #eeba00;");
    }

    private void commentError() {
        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("نمی توانید کامنت بگذارید");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        submit_comment.setTooltip(toolTip_username);
        submit_comment.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
    }

    public void PointButton() {
        String accountId = client.getClientInfo().getAccountId();
        List<String> answers = SendAndReceive.rate(productObject.getProductId(),
                accountId, pointArea.getText());

        errorHandler(answers);
        setStars();
    }

    private void errorHandler(List<String> answers) {

        Matcher matcher = Pattern.compile("^FAIL/(.+)/(.*)$")
                .matcher(answers.get(2));

        if (matcher.find()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(matcher.group(1));
            alert.setContentText(matcher.group(2));
            alert.showAndWait();
        }
    }

    public void CreateOffer() {
        CreatOffer.setMiniProduct(productObject);
        MainMenu.change(new CreatOffer().sceneBuilder());
    }

    public void downloadFile() {
        if (!productObject.getMediasId().equals("0"))
            SendAndReceive.getFileById(productObject.getMediasId());
    }
}
