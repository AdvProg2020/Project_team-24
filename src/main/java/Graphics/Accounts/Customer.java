package Graphics.Accounts;

import Controller.ControllerUnit;
import Controller.Controllers.BuyerController;
import Exceptions.*;
import Graphics.Cart;
import Graphics.LogHistoryCart;
import Graphics.MainMenu;
import Graphics.Menus.LogHistoryMenu;
import Graphics.Tools.SceneBuilder;
import Model.DataBase.DataBase;
import Model.Models.Account;
import Model.Models.DiscountCode;
import Model.Models.LogHistory;
import Model.Models.Structs.Medias;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
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
import java.util.*;
import java.util.stream.Collectors;

public class Customer implements Initializable, SceneBuilder {

    private static BuyerController buyerController = BuyerController.getInstance();
    private Model.Models.Accounts.Customer customer = (Model.Models.Accounts.Customer) ControllerUnit.getInstance().getAccount();
    private File selectedImage;

    @FXML
    private ImageView customer_image;
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
    private TableView<DiscountCode> DiscountCodes_Table;
    @FXML
    private TableColumn<DiscountCode, String> Codes;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/Account/CustomerAccount.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            username_txt.setText(customer.getUserName());
            password_txt.setText(customer.getPassword());
            lName_txt.setText(customer.getPersonalInfo().getList().getFieldByName("LastName").getString());
            fName_txt.setText(customer.getPersonalInfo().getList().getFieldByName("FirstName").getString());
            phone_txt.setText(customer.getPersonalInfo().getList().getFieldByName("PhoneNumber").getString());
            email_txt.setText(customer.getPersonalInfo().getList().getFieldByName("Email").getString());

            if (customer.getMediaId() != 0) {
                customer_image.setImage(Medias.getImage(Medias.getMediasById(customer.getMediaId()).getImageSrc()));
            }
        } catch (FieldDoesNotExistException | ProductMediaNotFoundException e) {
            e.printStackTrace();
        }
        balance_txt.setText(customer.getCredit() + "");

        customer.getDiscountCodeList().forEach(aLong -> {

            try {
                DiscountCode discountCode = DiscountCode.getDiscountCodeById(aLong);
                discountCode.checkExpiredDiscountCode(false);
            } catch (DiscountCodeExpiredException | AccountDoesNotExistException e) {
                e.printStackTrace();
            }
        });
        DiscountCodes_Table.setItems(FXCollections.observableArrayList(
                customer.getDiscountCodeList().stream().map(id -> {
                    try {
                        return DiscountCode.getDiscountCodeById(id);
                    } catch (DiscountCodeExpiredException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList())));
        Codes.setCellValueFactory(new PropertyValueFactory<>("productId"));

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

            customer.editField("password", password_txt.getText());
            customer.editField("balance", balance_txt.getText());
            customer.editField("FirstName", fName_txt.getText());
            customer.editField("LastName", lName_txt.getText());
            customer.editField("Email", email_txt.getText());
            customer.editField("PhoneNumber", phone_txt.getText());

        } catch (IOException | ProductMediaNotFoundException | FieldDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public void back() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    public void goCart() {
        MainMenu.change(new Cart().sceneBuilder());
    }

    public void goLogHistory() {
        List<LogHistory> logHistoryList = null;
        try {
            logHistoryList = buyerController.viewOrders();
        } catch (LogHistoryDoesNotExistException e) {
            e.printStackTrace();
        }
        LogHistoryMenu.setLogHistoryList(logHistoryList);
        LogHistoryCart.setLogHistoryList(logHistoryList);
        MainMenu.change(new LogHistoryMenu().sceneBuilder());
    }

    public void selectingImage() {
        FileChooser fc = new FileChooser();
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("image", "*.jpg"));
        selectedImage = fc.showOpenDialog(null);
        Image image = new Image(selectedImage.toURI().toString());
        customer_image.setImage(image);
    }

    public void DeleteAccount() {
        Account.deleteAccount(customer);
    }

    private void setImage() throws IOException, ProductMediaNotFoundException {
        String first = "src/main/resources/DataBase/Images/" + customer.getMediaId() + ".jpg";
        Files.copy(
                selectedImage.toPath(),
                Paths.get(first),
                StandardCopyOption.REPLACE_EXISTING
        );

        Medias medias;
        if (customer.getMediaId() == 0) {
            medias = new Medias();
            Medias.addMedia(medias);
            customer.setMediaId(medias.getId());
        } else {
            medias = Medias.getMediasById(customer.getMediaId());
        }
        medias.setImageSrc(new File(first).toURI().toString());
        DataBase.save(medias);
    }
}
