package A_Client.Graphics.Pages;

import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Tools.SceneBuilder;
import javafx.collections.FXCollections;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp implements SceneBuilder, Initializable {

    private static List<String> inputs;
    private static Mode mode;
    private static boolean state = true;
    private static List<TextField> texts;

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
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField Email;
    @FXML
    private TextField Phone;
    @FXML
    private TextField companyName;
    @FXML
    private TextField ComEmail;
    @FXML
    private TextField ComPhone;

    public static void setMode(Mode mode) {
        SignUp.mode = mode;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (state) {
            texts = Arrays.asList(username, passwordOne, passwordTow, firstName,
                    lastName, Email, Phone, companyName, ComEmail, ComPhone);

            chooseType.getItems().addAll(FXCollections.observableArrayList("Manager", "Seller", "Customer"));
            chooseType.setValue("Customer");
            Tooltip toolTip_username = new Tooltip();
            toolTip_username.setText("اسمت چیه فرزندم ...؟");
            username.setTooltip(toolTip_username);
            state = false;

            if (mode == Mode.ManagerMode) {
                chooseType.setValue("Manager");
                chooseType.setDisable(true);
            }
        }
        mode = Mode.NormalMode;
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

    public void goLogin() {
        MainMenu.change(new Login().sceneBuilder());
    }

    public void goMainMenu() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    public void back() {
        MainMenu.change(new SignUp().sceneBuilder());
    }

    public void goNext() {
        inputs = new ArrayList<>();
        String username = this.username.getText();
        String passOne = this.passwordOne.getText();
        String passTwo = this.passwordTow.getText();
        String typeMan = this.chooseType.getValue();

        reset_Base();

        if (!passOne.equals(passTwo)) {
            passwordNotMatch();
            return;

        } else if (mode == Mode.NormalMode) {
            inputs.add(typeMan);
            inputs.add(username);
        } else inputs.add(username);
        inputs.add(passOne);

        if (typeMan.equals("Seller")) {
            goSellerArea();
        } else goNextArea();
    }

    public void submitNext() {

        inputs.add(this.firstName.getText());
        inputs.add(this.lastName.getText());
        inputs.add(this.Email.getText());
        inputs.add(this.Phone.getText());

        reset_next();

        if (!(inputs.get(0).equals("Seller")) &&
                errorHandler(SendAndReceive.addAccount(inputs, inputs.get(0)))) goMainMenu();
    }

    public void submitSeller() {

        inputs.add(this.companyName.getText());
        inputs.add(this.ComEmail.getText());
        inputs.add(this.ComPhone.getText());

        reset_next();
        reset_seller();
        submitNext();

        if (errorHandler(SendAndReceive.addAccount(inputs, inputs.get(0)))) goMainMenu();
    }

    private void goSellerArea() {

        try {
            Scene scene = FXMLLoader.load(new File("src/main/resources/Graphics/Register/RegisterSeller.fxml").toURI().toURL());
            MainMenu.change(scene);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void goNextArea() {

        try {
            Scene scene = FXMLLoader.load(new File("src/main/resources/Graphics/Register/RegisterArea.fxml").toURI().toURL());
            MainMenu.change(scene);
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

    private void typeInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("تایپ نامعتبر است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        chooseType.setTooltip(toolTip_username);
        chooseType.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        status.setText("تایپ نامعتبر است.");
        failSound();
    }

    private void duplicatedUsername() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("نام کاربری تکراری است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        username.setTooltip(toolTip_username);
        username.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        status.setText("نام کاربری تکراری است.");
        failSound();
    }

    private void passwordInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("پسورد نادرست است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        passwordOne.setTooltip(toolTip_username);
        passwordOne.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        passwordTow.setTooltip(toolTip_username);
        passwordTow.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        status.setText("پسورد نادرست است.");
        failSound();
    }

    private void reset_Base() {

        username.setStyle("-fx-border-color: white;");
        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("Your username.");
        username.setTooltip(toolTip_username);

        passwordOne.setStyle("-fx-border-color: white;");
        passwordOne.setTooltip(null);

        passwordTow.setStyle("-fx-border-color: white;");
        passwordTow.setTooltip(null);

        chooseType.setStyle("-fx-border-color: white;");
        chooseType.setTooltip(null);
    }

    private static boolean errorHandler(String message) {

        Matcher matcher = Pattern.compile("^FAIL/(.+)/(.*)$").matcher(message);
        if (matcher.find()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(matcher.group(2));
            alert.showAndWait();

            return false;
        } else return true;
    }

    public static void failSound() {
        new Thread(() -> {
            MediaPlayer m = new MediaPlayer(new Media(new File("src/main/resources/Graphics/SoundEffect/failSound.mp3").toURI().toString()));
            m.play();
        }).start();
    }

    public enum Mode {
        NormalMode, ManagerMode
    }
}
