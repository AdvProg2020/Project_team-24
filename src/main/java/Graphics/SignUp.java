package Graphics;

import Graphics.Tools.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;

public class SignUp implements SceneBuilder {

    public TextField username;
    public PasswordField password;
    public Label status;

    @Override
    public Scene sceneBuilder() {
        return null;
    }

    public void register(ActionEvent actionEvent) {

        String usernameEntered = username.getText();
        String passwordEntered = password.getText();
        //controller + setting status.text
    }
}
