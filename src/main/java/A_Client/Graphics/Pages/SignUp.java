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
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp implements SceneBuilder, Initializable {

    private static List<String> inputs;
    private static Mode mode;
    private static boolean state = true;

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

        if (!passOne.equals(passTwo)) {
            AlertError("Passwords", "Not matched passwords...!");
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

        if (!(inputs.get(0).equals("Seller")))
            errorHandler(SendAndReceive.addAccount(inputs, inputs.get(0)));

        goMainMenu();
        state = true;
    }

    public void submitSeller() {

        inputs.add(this.companyName.getText());
        inputs.add(this.ComEmail.getText());
        inputs.add(this.ComPhone.getText());

        submitNext();
        errorHandler(SendAndReceive.addAccount(inputs, inputs.get(0)));
        goMainMenu();
        state = true;
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

    private static void errorHandler(String message) {
        Matcher matcher = Pattern.compile("^FAIL/(.+)/(.*)$").matcher(message);
        if (matcher.find()) {
            AlertError(matcher.group(1), matcher.group(2));
            failSound();
        }
    }

    private static void AlertError(String s1, String s2) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(s1);
        alert.setContentText(s2);
        alert.showAndWait();
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
