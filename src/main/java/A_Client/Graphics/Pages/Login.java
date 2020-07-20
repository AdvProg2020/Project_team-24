package A_Client.Graphics.Pages;

import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Tools.SceneBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
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

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("اسمت چیه فرزندم ...؟");
        username.setTooltip(toolTip_username);
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
        SignUp.setMode(SignUp.Mode.NormalMode);
        MainMenu.change(new SignUp().sceneBuilder());
    }

    public void goLogin() {

        String username = this.username.getText();
        String password = this.password.getText();

        reset();

        remember.isSelected();

        String accountId = SendAndReceive.Login(Arrays.asList(username, password));
        SendAndReceive.getClient().getClientInfo().setAccountId(accountId);

        goMainMenu();
    }

    public void goMainMenu() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    private void usernameTooShort() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("نام کاربری بیش از حد کوتاه است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        username.setTooltip(toolTip_username);
        username.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        status.setText("نام کاربری بیش از حد کوتاه است");
        failSound();
    }

    private void accountNotExist() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("نام کاربری نامعتبر است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        username.setTooltip(toolTip_username);
        username.setStyle("-fx-border-color: #bf2021;-fx-border-width: 4px");
        status.setText("نام کاربری نامعتبر است.");
        failSound();
    }

    private void usernameInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("از نام کاربریت خوشم نیومد.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        username.setTooltip(toolTip_username);
        username.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        status.setText("از نام کاربریت خوشم نیومد.");
        failSound();
    }

    private void passwordInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("پسورد نادرست است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        password.setTooltip(toolTip_username);
        password.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        failSound();
    }

    private void reset() {

        username.setStyle("-fx-border-color: white;");
        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("Your username.");
        username.setTooltip(toolTip_username);

        password.setStyle("-fx-border-color: white;");
        password.setTooltip(null);
    }

    public void failSound() {
        new Thread(() -> {
            MediaPlayer m = new MediaPlayer(new Media(new File("src/main/resources/Graphics/SoundEffect/failSound.mp3").toURI().toString()));
            m.play();
        }).start();
    }
}
