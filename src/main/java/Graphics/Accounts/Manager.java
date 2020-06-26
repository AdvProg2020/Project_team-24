package Graphics.Accounts;

import Controller.ControllerUnit;
import Controller.Controllers.BuyerController;
import Controller.Controllers.ManagerController;
import Exceptions.FieldDoesNotExistException;
import Graphics.MainMenu;
import Graphics.Tools.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Manager implements SceneBuilder, Initializable {

    private static ManagerController managerController = ManagerController.getInstance();
    private Model.Models.Accounts.Manager manager = (Model.Models.Accounts.Manager) ControllerUnit.getInstance().getAccount();
    private File selectedImage;

    @FXML
    private TextField Email;
    @FXML
    private Button back;
    @FXML
    private Button viewOffs;
    @FXML
    private Button editOff;
    @FXML
    private Button newOff;
    @FXML
    private Button creatManager;
    @FXML
    private Button viewAccounts;
    @FXML
    private Button deleteAccount;
    @FXML
    private Button viewCategories;
    @FXML
    private Button editCategories;
    @FXML
    private Button creatCategory;
    @FXML
    private TextField UserName;
    @FXML
    private TextField Password;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField LastName;
    @FXML
    private TextField PhoneNum;

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/Account/ManagerAccount.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserName.setText(manager.getUserName());
        Password.setText(manager.getPassword());
        try {
            LastName.setText(manager.getPersonalInfo().getList().getFieldByName("LastName").getString());
            FirstName.setText(manager.getPersonalInfo().getList().getFieldByName("FirstName").getString());
            PhoneNum.setText(manager.getPersonalInfo().getList().getFieldByName("PhoneNumber").getString());
            Email.setText(manager.getPersonalInfo().getList().getFieldByName("Email").getString());
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public void ChoosePhoto() {
    }

    public void AddCategory(ActionEvent event) {
    }

    public void EditCategory(ActionEvent event) {
    }

    public void CategoryList(ActionEvent event) {
    }

    public void DeleteAccount(ActionEvent event) {
    }

    public void UserList(ActionEvent event) {
    }

    public void CreateManager(ActionEvent event) {
    }

    public void CreateDiscountCode(ActionEvent event) {
    }

    public void EditDiscountCode(ActionEvent event) {
    }

    public void DiscountCodeList(ActionEvent event) {

    }

    public void BackToMainMenu() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }
}
