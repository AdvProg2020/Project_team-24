package A_Client.Graphics.Creates;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Tools.SceneBuilder;
import A_Client.MiniModels.Structs.MiniDiscountCode;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateDiscountCode implements SceneBuilder, Initializable {

    private final Client client = SendAndReceive.getClient();
    private static Mode mode = Mode.New;

    @FXML
    private TextField start_date;
    @FXML
    private TextField end_date;
    @FXML
    private TextField percent_discount;
    @FXML
    private TextField limit_discount;
    @FXML
    private Button submit_btn;
    @FXML
    private TextField numberOfUse;

    public static void setMode(Mode mode) {
        CreateDiscountCode.mode = mode;
    }

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
        if (mode == Mode.Edit) init_editMode();
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

        if (mode == Mode.New)
            submit_addMode(start, end, percent, limit, num);
        if (mode == Mode.Edit)
            submit_editMode(start, end, percent, limit, num);

        goMainMenu();
    }

    private void submit_addMode(String start, String end, String percent, String limit, String num) {
        ArrayList<String> objects = new ArrayList<>();
        objects.add(start);
        objects.add(end);
        objects.add(percent);
        objects.add(limit);
        objects.add(num);
        SendAndReceive.addDiscountCode(objects);
    }

    private void submit_editMode(String start, String end, String percent, String limit, String num) {
        RequestForEdit("start", start);
        RequestForEdit("end", end);
        RequestForEdit("frequentUse", num);
        RequestForEdit("maxDiscountAmount", limit);
        RequestForEdit("discountPercent", percent);
    }

    private void RequestForEdit(String fieldName, String fieldValue) {
        ArrayList<String> objects = new ArrayList<>();
        objects.add(client.getClientInfo().getCodeId());
        objects.add(fieldName);
        objects.add(fieldValue);
        SendAndReceive.EditDiscountCode(objects);
    }

    private void init_editMode() {
        MiniDiscountCode discountCode = SendAndReceive.getCodeById(client.getClientInfo().getCodeId());
        start_date.setText(discountCode.getStart().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        end_date.setText(discountCode.getEnd().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        percent_discount.setText(discountCode.getDiscountCodePercent() + "");
        limit_discount.setText(discountCode.getDiscountCodeLimit() + "");
        numberOfUse.setText(discountCode.getFrequent() + "");
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
                )).play()
        ).start();
    }

    public enum Mode {
        Edit, New
    }
}
