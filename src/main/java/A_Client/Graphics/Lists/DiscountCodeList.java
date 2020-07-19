package A_Client.Graphics.Lists;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Creates.CreateDiscountCode;
import A_Client.Graphics.Tools.SceneBuilder;
import A_Client.Graphics.MainMenu;
import Structs.MiniDiscountCode;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DiscountCodeList implements SceneBuilder, Initializable {

    private final Client client = SendAndReceive.getClient();
    @FXML
    private TableView<MiniDiscountCode> discountCodeList;
    @FXML
    private TableColumn<MiniDiscountCode, Double> maxDiscount;
    @FXML
    private TableColumn<MiniDiscountCode, String> discount;
    @FXML
    private TableColumn<MiniDiscountCode, LocalDate> endDate;
    @FXML
    private TableColumn<MiniDiscountCode, LocalDate> startDate;
    @FXML
    private TableColumn<MiniDiscountCode, String> code;
    @FXML
    private MediaView gif;
    @FXML
    private TableColumn<MiniDiscountCode, Pane> buttons;

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src\\main\\resources\\Graphics\\DiscountCodeList\\DiscountCodeList.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        gifPlay();
    }

    private void gifPlay() {
        MediaPlayer value = new MediaPlayer(new Media(new File("src\\main\\resources\\Graphics\\DiscountCodeList\\slae.mp4").toURI().toString()));
        gif.setMediaPlayer(value);
        value.setCycleCount(Integer.MAX_VALUE);
        value.play();
    }

    private void init() {
        List<MiniDiscountCode> list = SendAndReceive.getAllDiscountCodes();
        discountCodeList.setItems(FXCollections.observableList(list));
        code.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDiscountCode()));
        startDate.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getStart()));
        endDate.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getEnd()));
        discount.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDiscountCodePercent() + ""));
        maxDiscount.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDiscountCodeLimit()));
        buttons.setCellValueFactory(param -> new SimpleObjectProperty<>(setChoicePane(param.getValue())));
    }

    @NotNull
    @Contract("_ -> new")
    private Pane setChoicePane(MiniDiscountCode discountCode) {

        Button editButton = new Button("ویرایش اطلاعات");
        Button addAccountButton = new Button("افزودن این کد تحفیف به کاربر");

        HBox hBox = new HBox();

        editButton.setOnAction(event -> {
            SetCurrentDiscountCode(discountCode);
            CreateDiscountCode.setMode(CreateDiscountCode.Mode.Edit);
            MainMenu.change(new CreateDiscountCode().sceneBuilder());
        });

        addAccountButton.setOnAction(event -> {
            SetCurrentDiscountCode(discountCode);
            AccountsList.setMode(AccountsList.Mode.addDiscountCode);
            MainMenu.change(new AccountsList().sceneBuilder());
        });

        hBox.getChildren().addAll(editButton, addAccountButton);

        return new Pane(hBox);
    }

    private void SetCurrentDiscountCode(MiniDiscountCode discountCode) {
        client.getClientInfo().setCodeId(discountCode.getId());
        SendAndReceive.SetCurrentCode(discountCode.getId());
    }
}
