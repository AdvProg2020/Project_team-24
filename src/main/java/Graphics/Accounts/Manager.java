package Graphics.Accounts;

import Graphics.Tools.SceneBuilder;
import javafx.event.ActionEvent;
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

    public Button back;
    public Button viewOffs;
    public Button editOff;
    public Button newOff;
    public Button creatManager;
    public Button viewaccounts;
    public Button deleteAccount;
    public Button viewCategories;
    public Button editCategories;
    public Button creatCategory;
    public Label role;
    public TextField UserName;
    public TextField AccountType;
    public TextField Password;
    public TextField FirstName;
    public TextField LastName;
    public TextField PhoneNum;

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

    public void BackToMainMenu(ActionEvent event) {
    }
}
