package Graphics;

import Controller.ControllerUnit;
import Controller.Controllers.BuyerController;
import Exceptions.*;
import Graphics.Tools.SceneBuilder;
import Model.Models.FieldList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Payment implements Initializable, SceneBuilder {

    @FXML
    private TextField address;
    @FXML
    private TextField postCode;
    @FXML
    private TextField discountCode;
    @FXML
    private Button payment_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FieldList list = ControllerUnit.getInstance()
                .getAccount().getPersonalInfo()
                .getList();
        try {

            if (list.isFieldWithThisName("postCode"))
                postCode.setText((list.getFieldByName("postCode")).getString());

            if (list.isFieldWithThisName("address"))
                address.setText((list.getFieldByName("address")).getString());

        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/Payment/Payment.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public void pay() {

        BuyerController instance = BuyerController.getInstance();

        String postCode = this.postCode.getText();
        String address = this.address.getText();
        String discountCode = this.discountCode.getText();

        reset();

        try {

            instance.receiveInformation(postCode, address);
            instance.discountCodeUse(discountCode);
            instance.buyProductsOfCart();

        } catch (PostCodeInvalidException e) {
            invalidPostCode();
        } catch (AddresInvalidException e) {
            invalidAddress();
        } catch (InvalidDiscountCodeException e) {
            invalidDiscountCode();
        } catch (DiscountCodeExpiredException e) {
            discountCodeExpired();
        } catch (NotEnoughCreditException e) {
            notEnoughCredit();
        } catch (ProductDoesNotExistException | FieldDoesNotExistException | AccountDoesNotExistException | SellerDoesNotSellOfThisProduct e) {
            e.printStackTrace();
        }
    }

    private void reset() {
        postCode.setStyle("-fx-border-color: white;");
        postCode.setTooltip(null);
        address.setStyle("-fx-border-color: white;");
        address.setTooltip(null);
        discountCode.setStyle("-fx-border-color: white;");
        discountCode.setTooltip(null);
        payment_btn.setStyle("-fx-border-color: white;");
        payment_btn.setTooltip(null);
    }

    private void invalidPostCode() {
        Tooltip postCode_tooltip = new Tooltip();
        postCode_tooltip.setText("کد پستی نامعتبر است");
        postCode_tooltip.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        postCode.setTooltip(postCode_tooltip);
        postCode.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        failSound();
    }

    private void invalidAddress() {
        Tooltip address_tooltip = new Tooltip();
        address_tooltip.setText("آدرس نامعتبر است");
        address_tooltip.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        address.setTooltip(address_tooltip);
        address.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        failSound();
    }

    private void invalidDiscountCode() {
        Tooltip code_tooltip = new Tooltip();
        code_tooltip.setText("کد تهفیف نامعتبر است");
        code_tooltip.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        discountCode.setTooltip(code_tooltip);
        discountCode.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        failSound();
    }

    private void discountCodeExpired() {
        Tooltip code_tooltip = new Tooltip();
        code_tooltip.setText("کد تهفیف منقضی شده است");
        code_tooltip.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        discountCode.setTooltip(code_tooltip);
        discountCode.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        failSound();
    }

    private void notEnoughCredit() {
        Tooltip pay_tooltip = new Tooltip();
        pay_tooltip.setText("اخی ... پول نداری!");
        pay_tooltip.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        payment_btn.setTooltip(pay_tooltip);
        payment_btn.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        payment_btn.setDisable(true);
        failSound();
    }

    private void failSound() {
        new Thread(() -> new MediaPlayer(
                new Media(
                        new File("src/main/resources/Graphics/SoundEffect/failSound.mp3").toURI().toString()
                )).play()).start();
    }
}
