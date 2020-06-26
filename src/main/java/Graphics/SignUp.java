package Graphics;

import Controller.Controllers.SignUpController;
import Exceptions.*;
import Graphics.Tools.SceneBuilder;
import Model.Models.Account;
import Model.Models.Accounts.Seller;
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

    private Account account = null;
    private SignUpController instance = null;

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

    public void goLogin() {
        MainMenu.change(new Login().sceneBuilder());
    }

    public void goMainMenu() {
        MainMenu.change(new MainMenu().sceneBuilder());
    }

    public void back() {
        MainMenu.change(new SignUp().sceneBuilder());
    }

    public void goNext() {

        instance = SignUpController.getInstance();

        String username = this.username.getText();
        String passOne = this.passwordOne.getText();
        String passTwo = this.passwordTow.getText();
        String typeMan = this.chooseType.getValue();

        try {

            reset_Base();

            account = instance.creatTheBaseOfAccount(typeMan, username);

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
            typeInvalid();
        } catch (ThisUserNameAlreadyExistsException e) {
            duplicatedUsername();
        } catch (PasswordInvalidException e) {
            passwordInvalid();
        }
    }

    private void goSellerArea() {

        try {
            Scene scene = FXMLLoader.load(new File("src/main/resources/Graphics/Register/RegisterSeller.fxml").toURI().toURL());
            MainMenu.getPrimaryStage().setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void goNextArea() {

        try {
            Scene scene = FXMLLoader.load(new File("src/main/resources/Graphics/Register/RegisterArea.fxml").toURI().toURL());
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
        MainMenu.failSound();
    }

    private void usernameInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("از نام کاربریت خوشم نیومد.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        username.setTooltip(toolTip_username);
        username.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        status.setText("از نام کاربریت خوشم نیومد.");
        MainMenu.failSound();
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
        MainMenu.failSound();
    }

    private void typeInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("تایپ نامعتبر است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        chooseType.setTooltip(toolTip_username);
        chooseType.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        status.setText("تایپ نامعتبر است.");
        MainMenu.failSound();
    }

    private void duplicatedUsername() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("نام کاربری تکراری است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        username.setTooltip(toolTip_username);
        username.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        status.setText("نام کاربری تکراری است.");
        MainMenu.failSound();
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
        MainMenu.failSound();
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

    public void submitNext() {

        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String email = this.Email.getText();
        String phone = this.Phone.getText();

        reset_next();

        try {

            instance.savePersonalInfo(account,firstName,lastName,phone,email);
        } catch (FirstNameInvalidException e) {
            firstNameInvalid();
        } catch (LastNameInvalidException e) {
            lastNameInvalid();
        } catch (EmailInvalidException e) {
            emailInvalid();
        } catch (PhoneNumberInvalidException e) {
            phoneInvalid();
        }
    }

    private void firstNameInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("نام نامعتبر است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        firstName.setTooltip(toolTip_username);
        firstName.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        MainMenu.failSound();
    }

    private void lastNameInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("فامیلی نامعتبر است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        lastName.setTooltip(toolTip_username);
        lastName.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        MainMenu.failSound();
    }

    private void emailInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("ایمیل نامعتبر است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        Email.setTooltip(toolTip_username);
        Email.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        MainMenu.failSound();
    }

    private void phoneInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("شماره نامعتبر است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        Phone.setTooltip(toolTip_username);
        Phone.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        MainMenu.failSound();
    }

    private void reset_next() {

        firstName.setStyle("-fx-border-color: white;");
        firstName.setTooltip(null);

        lastName.setStyle("-fx-border-color: white;");
        lastName.setTooltip(null);

        Phone.setStyle("-fx-border-color: white;");
        Phone.setTooltip(null);

        Email.setStyle("-fx-border-color: white;");
        Email.setTooltip(null);
    }

    public void submitSeller() {

        String companyName = this.companyName.getText();
        String comEmail = this.ComEmail.getText();
        String comPhone = this.ComPhone.getText();

        reset_next();
        reset_seller();

        try {

            submitNext();

            instance.saveCompanyInfo(account,companyName,comPhone,comEmail);

        } catch (EmailInvalidException e) {
            comEmailInvalid();
        } catch (PhoneNumberInvalidException e) {
            comPhoneInvalid();
        } catch (CompanyNameInvalidException e) {
            companyNameInvalid();
        }
    }

    private void reset_seller() {

        companyName.setStyle("-fx-border-color: white;");
        companyName.setTooltip(null);

        ComEmail.setStyle("-fx-border-color: white;");
        ComEmail.setTooltip(null);

        ComPhone.setStyle("-fx-border-color: white;");
        ComPhone.setTooltip(null);
    }

    private void companyNameInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("نام شرکت نامعتبر است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        companyName.setTooltip(toolTip_username);
        companyName.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        MainMenu.failSound();
    }

    private void comEmailInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("ایمیل شرکت نامعتبر است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        ComEmail.setTooltip(toolTip_username);
        ComEmail.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        MainMenu.failSound();
    }

    private void comPhoneInvalid() {

        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("شماره شرکت نامعتبر است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        ComPhone.setTooltip(toolTip_username);
        ComPhone.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        MainMenu.failSound();
    }
}
