package Graphics.Accounts;

import Controller.ControllerUnit;
import Controller.Controllers.SellerController;
import Exceptions.FieldDoesNotExistException;
import Exceptions.LogHistoryDoesNotExistException;
import Exceptions.ProductMediaNotFoundException;
import Graphics.MainMenu;
import Graphics.Tools.SceneBuilder;
import Model.Models.Account;
import Model.Models.LogHistory;
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
            // Product Info
            lName_txt.setText(seller.getPersonalInfo().getList().getFieldByName("lName").getString());
            fName_txt.setText(seller.getPersonalInfo().getList().getFieldByName("fName").getString());
            phone_txt.setText(seller.getPersonalInfo().getList().getFieldByName("phone").getString());
            email_txt.setText(seller.getPersonalInfo().getList().getFieldByName("email").getString());
            // Product Info
            bran_txt.setText(seller.getCompanyInfo().getList().getFieldByName("brand").getString());
            comEmail_txt.setText(seller.getCompanyInfo().getList().getFieldByName("comEmail").getString());
            comPhone_txt.setText(seller.getCompanyInfo().getList().getFieldByName("comPhone").getString());
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }
        balance_txt.setText(seller.getBalance() + "");
        try {

            if (seller.getMediaId() != 0) {
                seller_image.setImage(Medias.getMediasById(seller.getMediaId()).getImage());
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
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("image","*.jpg"));
        selectedImage = fc.showOpenDialog(null);
        Image image = new Image(selectedImage.toURI().toString());
        seller_image.setImage(image);
    }

    public void showProducts() {
        // ?
    }

    public void showLogHistories() {
        // ?
    }

    public void logout() {
        ControllerUnit.getInstance().setAccount(null);
        back();
    }

    public void submit() {

        if (selectedImage != null) {
            try {

                String first = "src/main/resources/DataBase/Images/" + seller.getMediaId() + ".jpg";
                Files.copy(
                        selectedImage.toPath(),
                        Paths.get(first),
                        StandardCopyOption.REPLACE_EXISTING
                );
                Medias.getMediasById(seller.getMediaId()).setImage(new Image(new File(first).toURI().toString()));

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
    }

    public void back() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }
}
