package Graphics;

import Controller.Controllers.SignUpController;
import Exceptions.*;
import Graphics.Login;
import Graphics.MainMenu;
import Graphics.Tools.SceneBuilder;
import Model.Models.Account;
import Model.Models.Accounts.Seller;
import com.sun.org.glassfish.external.amx.AMX;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;

public class SignUp implements SceneBuilder, Initializable {

    private Account account;

    @FXML
    private ChoiceBox<String> chooseType;
    @FXML
    private Label status;
    @FXML
    private TextField username;
    @FXML
    private PasswordField passwordOne;
    @FXML
    private PasswordField passwordTow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        chooseType.getItems().addAll("Manager", "Seller", "Customer");

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("اسمت چیه فرزندم ...؟");
        username.setTooltip(toolTip_username);
    }

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/Register/SignUp_Base.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public void goMainMenu() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    public void goNext() {

        String username = this.username.getText();
        String passOne = this.passwordOne.getText();
        String passTwo = this.passwordTow.getText();
        String typeMan = this.chooseType.getValue();

        try {

            SignUpController instance = SignUpController.getInstance();

            Account account = instance.creatTheBaseOfAccount(typeMan, username);

            if (!passOne.equals(passTwo)) {
                passwordNotMatch();
                return;
            }

            instance.creatPasswordForAccount(account, passOne);

            if (account instanceof Seller) {
                goSellerArea();
            } else goNextArea();

        } catch (UserNameInvalidException e) {
            usernameInvalid();
        } catch (UserNameTooShortException e) {
            usernameTooShort();
        } catch (TypeInvalidException | CanNotCreatMoreThanOneMangerBySignUp e) {
            e.printStackTrace();
        } catch (ThisUserNameAlreadyExistsException e) {
            e.printStackTrace();
        } catch (PasswordInvalidException e) {
            e.printStackTrace();
        }
    }

    public void goLogin() {
        MainMenu.getPrimaryStage().setScene(new Login().sceneBuilder());
    }

    private void goSellerArea() {

        try {
            Scene scene = FXMLLoader.load(new File("src/main/resources/Graphics/Register/SignUp_Base.fxml").toURI().toURL());
            MainMenu.getPrimaryStage().setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void goNextArea() {

        try {
            Scene scene = FXMLLoader.load(new File("src/main/resources/Graphics/Register/SignUp_Base.fxml").toURI().toURL());
            MainMenu.getPrimaryStage().setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
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

    private void usernameInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("از نام کاربریت خوشم نیومد.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        username.setTooltip(toolTip_username);
        username.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        status.setText("از نام کاربریت خوشم نیومد.");
        failSound();
    }

    private void passwordNotMatch() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("دو پسورد یکسان نیستند.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        passwordOne.setTooltip(toolTip_username);
        passwordOne.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        passwordTow.setTooltip(toolTip_username);
        passwordTow.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        status.setText("دو پسورد یکسان نیستند.");
        failSound();
    }

    private void failSound() {
        new Thread(() -> {
            new MediaPlayer(
                    new Media(
                            new File("src/main/resources/Graphics/SoundEffect/failSound.mp3").toURI().toString()
                    )).play();
        }).start();
    }
}
