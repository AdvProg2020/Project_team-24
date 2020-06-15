package Graphics;

import Graphics.Tools.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;

public class Login implements SceneBuilder {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label status;
    @FXML
    private CheckBox remember;

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

    public void login(ActionEvent actionEvent) {

        String usernameEntered = username.getText();
        String passwordEntered = password.getText();
        //controller + setting status.text
    }

    public void goSignUp() {
    }

    public void goLogin() {
    }

    public void goMainMenu(ActionEvent actionEvent) {

    }
}
