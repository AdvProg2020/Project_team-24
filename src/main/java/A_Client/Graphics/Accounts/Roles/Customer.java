package A_Client.Graphics.Accounts.Roles;

import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Accounts.BaseAccount;
import A_Client.Graphics.Pages.Cart;
import A_Client.Graphics.Models.LogHistoryCart;
import Exceptions.*;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Menus.LogHistoryMenu;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.MiniDiscountCode;
import Structs.MiniLogHistory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Customer extends BaseAccount implements Initializable, SceneBuilder {

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
    private TableView<MiniDiscountCode> DiscountCodes_Table;
    @FXML
    private TableColumn<MiniDiscountCode, String> Codes;

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

        account = SendAndReceive.getAccountById(client.getClientInfo().getAccountId());

        try {
            username_txt.setText(account.getUsername());
            password_txt.setText(account.getPassword());
            lName_txt.setText(account.getPersonalInfo().getFieldByName("LastName").getString());
            fName_txt.setText(account.getPersonalInfo().getFieldByName("FirstName").getString());
            phone_txt.setText(account.getPersonalInfo().getFieldByName("PhoneNumber").getString());
            email_txt.setText(account.getPersonalInfo().getFieldByName("Email").getString());

            if (account.getMediasId() == null) ImageInit(customer_image);

        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }
        balance_txt.setText(account.getWallet().getBalance() + "");

        SendAndReceive.CheckMyDiscountCodes();

        DiscountCodes_Table.setItems(FXCollections.observableArrayList(SendAndReceive.GetCodesOfUserById(client.getClientInfo().getAccountId())));
        Codes.setCellValueFactory(new PropertyValueFactory<>("productId"));

    }

    public void logout() {
        super.logout();
        back();
    }

    public void submit() {
        if (selectedImage != null) setImage();
        RequestForEdit("password", password_txt.getText());
        RequestForEdit("balance", balance_txt.getText());
        RequestForEdit("FirstName", fName_txt.getText());
        RequestForEdit("LastName", lName_txt.getText());
        RequestForEdit("Email", email_txt.getText());
        RequestForEdit("PhoneNumber", phone_txt.getText());
    }

    public void goCart() {
        MainMenu.change(new Cart().sceneBuilder());
    }

    public void goLogHistory() {
        List<MiniLogHistory> logHistoryList = SendAndReceive.
                GetLogsOfUserById(client.getClientInfo().getAccountId());

        LogHistoryMenu.setLogHistoryList(logHistoryList);
        LogHistoryCart.setLogHistoryList(logHistoryList);

        MainMenu.change(new LogHistoryMenu().sceneBuilder());
    }

    public void selectingImage() {
        super.selectFile(customer_image);
    }

    public void DeleteAccount() {
        SendAndReceive.DeleteAccountById(client.getClientInfo().getAccountId());
    }

    private void setImage() {
        String first = "src/main/resources/DataBase/Images/"
                + account.getMediasId() + ".jpg";
        setImage(first);
    }
}
