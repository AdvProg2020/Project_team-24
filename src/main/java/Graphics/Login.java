package Graphics;

import Exceptions.AccountDoesNotExistException;
import Graphics.Tools.SceneBuilder;
import Model.Models.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements SceneBuilder, Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label status;
    @FXML
    private CheckBox remember;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // for remember mode.
    }

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/Login/Login.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public void goSignUp() {
        MainMenu.change(new Login().sceneBuilder());
    }

    public void goLogin() {
        try {
            Account account = login();

            if (account == null) {
                // wrong password
            }

        } catch (AccountDoesNotExistException e) {
            // user not found.
        }

        // change main menu
        goMainMenu();
    }

    public void goMainMenu() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    @Nullable
    private Account login() throws AccountDoesNotExistException {

        String username = this.username.getText();
        Account account = Account.getAccountByUserName(username);

        String password = this.password.getText();
        if (!password.equals(account.getPassword())) return null;

        return account;
    }
}
