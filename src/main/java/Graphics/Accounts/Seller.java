package Graphics.Accounts;

import Controller.ControllerUnit;
import Controller.Controllers.SellerController;
import Exceptions.FieldDoesNotExistException;
import Exceptions.LogHistoryDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Exceptions.ProductMediaNotFoundException;
import Graphics.Creates.CreateAuction;
import Graphics.Creates.CreateCategory;
import Graphics.Creates.CreateProduct;
import Graphics.Menus.LogHistoryMenu;
import Graphics.MainMenu;
import Graphics.Menus.AuctionsMenu;
import Graphics.Menus.ProductsMenu;
import Graphics.Models.AuctionCart;
import Graphics.Models.ProductCart;
import Graphics.Tools.SceneBuilder;
import Model.DataBase.DataBase;
import Model.Models.Auction;
import Model.Models.Product;
import Model.Models.Structs.Medias;
import Model.Models.Structs.ProductLog;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Seller implements SceneBuilder, Initializable {

    private static SellerController sellerController = SellerController.getInstance();
    private Model.Models.Accounts.Seller seller = (Model.Models.Accounts.Seller) ControllerUnit.getInstance().getAccount();
    private File selectedImage;

    @FXML
    private ImageView seller_image;
    @FXML
    private TextField comEmail_txt;
    @FXML
    private TextField comPhone_txt;
    @FXML
    private TextField bran_txt;
    @FXML
    private TextField balance_txt;
    @FXML
    private TextField email_txt;
    @FXML
    private TextField phone_txt;
    @FXML
    private TextField password_txt;
    @FXML
    private TextField username_txt;
    @FXML
    private TextField lName_txt;
    @FXML
    private TextField fName_txt;
    @FXML
    private TableView<ProductLog> soldTable;
    @FXML
    private TableColumn<ProductLog, String> prices_column;
    @FXML
    private TableColumn<ProductLog, String> sold_column;
    @FXML
    private TableColumn<ProductLog, String> logId_column;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/Account/SellerAccount.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username_txt.setText(seller.getUserName());
        password_txt.setText(seller.getPassword());
        try {
            lName_txt.setText(seller.getPersonalInfo().getList().getFieldByName("LastName").getString());
            fName_txt.setText(seller.getPersonalInfo().getList().getFieldByName("FirstName").getString());
            phone_txt.setText(seller.getPersonalInfo().getList().getFieldByName("PhoneNumber").getString());
            email_txt.setText(seller.getPersonalInfo().getList().getFieldByName("Email").getString());
            bran_txt.setText(seller.getCompanyInfo().getList().getFieldByName("CompanyName").getString());
            comEmail_txt.setText(seller.getCompanyInfo().getList().getFieldByName("CompanyEmail").getString());
            comPhone_txt.setText(seller.getCompanyInfo().getList().getFieldByName("CompanyPhoneNumber").getString());
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }
        balance_txt.setText(seller.getBalance() + "");
        try {

            if (seller.getMediaId() != 0) {
                seller_image.setImage(Medias.getImage(Medias.getMediasById(seller.getMediaId()).getImageSrc()));
            }

            List<ProductLog> productLogList = new ArrayList<>();
            sellerController.viewSalesHistory()
                    .forEach(logHistory -> productLogList.addAll(logHistory.getProductLogList()));

            soldTable.setItems(FXCollections.observableArrayList(productLogList));
            logId_column.setCellValueFactory(new PropertyValueFactory<>("productId"));
            sold_column.setCellValueFactory(new PropertyValueFactory<>("productName"));
            prices_column.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
        } catch (LogHistoryDoesNotExistException | ProductMediaNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void selectingImage() {
        FileChooser fc = new FileChooser();
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("image", "*.jpg"));
        selectedImage = fc.showOpenDialog(null);
        Image image = new Image(selectedImage.toURI().toString());
        seller_image.setImage(image);
    }

    public void showProducts() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        try {
            setProducts(sellerController.showProducts());
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
        }
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    public void showLogHistories() {
        MainMenu.change(new LogHistoryMenu().sceneBuilder());
    }

    public void logout() {
        ControllerUnit.getInstance().setAccount(null);
        back();
    }

    public void submit() {

        try {

            if (selectedImage != null) {
                setImage();
            }

            seller.editField("password", password_txt.getText());
            seller.editField("balance", balance_txt.getText());
            seller.editField("FirstName", fName_txt.getText());
            seller.editField("LastName", lName_txt.getText());
            seller.editField("Email", email_txt.getText());
            seller.editField("PhoneNumber", phone_txt.getText());
            seller.editField("CompanyName", bran_txt.getText());
            seller.editField("CompanyPhoneNumber", comPhone_txt.getText());
            seller.editField("CompanyEmail", comEmail_txt.getText());

        } catch (IOException | ProductMediaNotFoundException | FieldDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    private void setImage() throws IOException, ProductMediaNotFoundException {
        String first = "src/main/resources/DataBase/Images/" + seller.getMediaId() + ".jpg";
        Files.copy(
                selectedImage.toPath(),
                Paths.get(first),
                StandardCopyOption.REPLACE_EXISTING
        );

        Medias medias;
        if (seller.getMediaId() == 0) {
            medias = new Medias();
            Medias.addMedia(medias);
            seller.setMediaId(medias.getId());
        } else {
            medias = Medias.getMediasById(seller.getMediaId());
        }
        medias.setImageSrc(new File(first).toURI().toString());
        DataBase.save(medias);
    }

    public void back() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    public void newCategory() {
        MainMenu.change(new CreateCategory().sceneBuilder());
    }

    public void newProduct() {
        MainMenu.change(new CreateProduct().sceneBuilder());
    }

    public void createAuction() {
        MainMenu.change(new CreateAuction().sceneBuilder());
    }

    public void showAuctions() {
        ArrayList<Auction> list = new ArrayList<>((sellerController.viewAllOffs()));
        AuctionsMenu.setList(list);
        AuctionCart.setAuctionList(list);
        MainMenu.change(new AuctionsMenu().sceneBuilder());
    }

    private void setProducts(List<Product> list) {
        ProductsMenu.setList(list);
        ProductCart.setProductList(list);
    }
}
