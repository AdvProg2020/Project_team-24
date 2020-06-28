package Graphics.Creates;

import Controller.ControllerUnit;
import Controller.Controllers.ManagerController;
import Exceptions.InvalidInputByUserException;
import Exceptions.InvalidStartAndEndDateForDiscountCodeException;
import Exceptions.ProductCantBeInMoreThanOneAuction;
import Exceptions.ProductDoesNotExistException;
import Graphics.MainMenu;
import Graphics.Tools.SceneBuilder;
import Model.Models.Auction;
import Model.Models.DiscountCode;
import Model.Models.Structs.Discount;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CreateDiscountCode implements SceneBuilder, Initializable {

    private static ManagerController managerController = ManagerController.getInstance();
    private static Mode mode = Mode.New;

    public TextField start_date;
    public TextField end_date;
    public TextField percent_discount;
    public TextField limit_discount;
    public Button submit_btn;
    public TextField numberOfUse;

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src\\main\\resources\\Graphics\\CreatePages\\CreateDiscountCode\\CreateDiscountCode.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (mode == Mode.Edit) {
            DiscountCode discount = ControllerUnit.getInstance().getCurrentDiscountCode();
            start_date.setText(discount.getStart() + "");
            end_date.setText(discount.getEnd() + "");
            percent_discount.setText(discount.getDiscount().getPercent() + "");
            limit_discount.setText(discount.getDiscount().getAmount() + "");
            numberOfUse.setText(discount.getFrequentUse() + "");
        }
    }

    public void submit() {

        String start = start_date.getText();
        String end = end_date.getText();
        String percent = percent_discount.getText();
        String limit = limit_discount.getText();
        String num = numberOfUse.getText();

        reset();

        if (start.isEmpty() || end.isEmpty() || percent.isEmpty() || limit.isEmpty() || num.isEmpty()) {
            mustBeFilled();
            return;
        }

        try {
            DiscountCode discountCode = managerController.creatDiscountCode(start, end, percent, limit, num);

            if (mode == Mode.Edit) {

                DiscountCode currentDiscountCode = ControllerUnit.getInstance().getCurrentDiscountCode();
            }

            goMainMenu();
        } catch (InvalidStartAndEndDateForDiscountCodeException e) {
            InvalidEndAndStart();
        }
    }

    private void InvalidEndAndStart() {
        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("تاریح اشتباه است.");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        start_date.setTooltip(toolTip_username);
        start_date.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        end_date.setTooltip(toolTip_username);
        end_date.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        failSound();
    }

    private void goMainMenu() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    private void reset() {
        start_date.setTooltip(null);
        start_date.setStyle("-fx-border-color: white;");
        end_date.setTooltip(null);
        end_date.setStyle("-fx-border-color: white;");
        percent_discount.setTooltip(null);
        percent_discount.setStyle("-fx-border-color: white;");
        limit_discount.setTooltip(null);
        limit_discount.setStyle("-fx-border-color: white;");
        submit_btn.setTooltip(null);
        submit_btn.setStyle("-fx-border-color: white;");
    }

    private void mustBeFilled() {
        Tooltip mustFilled = new Tooltip();
        mustFilled.setText("این فیلد را باید پر کنید");
        mustFilled.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        submit_btn.setTooltip(mustFilled);
        submit_btn.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        failSound();
    }

    private void failSound() {
        new Thread(() -> new MediaPlayer(
                new Media(
                        new File("src/main/resources/Graphics/SoundEffect/failSound.mp3").toURI().toString()
                )).play()).start();
    }

    public enum Mode {
        Edit, New
    }
}
