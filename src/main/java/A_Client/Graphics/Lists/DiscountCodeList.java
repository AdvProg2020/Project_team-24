package A_Client.Graphics.Lists;

import A_Client.Graphics.Creates.CreateDiscountCode;
import A_Client.Graphics.Tools.SceneBuilder;
import B_Server.Controller.ControllerUnit;
import B_Server.Controller.Controllers.ManagerController;
import A_Client.Graphics.MainMenu;
import B_Server.Model.Models.DiscountCode;
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

    private static ManagerController managerController = ManagerController.getInstance();

    @FXML
    private TableView<DiscountCode> discountCodeList;
    @FXML
    private TableColumn<DiscountCode, Double> maxDiscount;
    @FXML
    private TableColumn<DiscountCode, String> discount;
    @FXML
    private TableColumn<DiscountCode, LocalDate> endDate;
    @FXML
    private TableColumn<DiscountCode, LocalDate> startDate;
    @FXML
    private TableColumn<DiscountCode, String> code;
    @FXML
    private MediaView gif;
    @FXML
    private TableColumn<DiscountCode, Pane> buttons;

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
        List<DiscountCode> list = managerController.viewDiscountCodes();
        discountCodeList.setItems(FXCollections.observableList(list));
        code.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDiscountCode()));
        startDate.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getStart()));
        endDate.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getEnd()));
        discount.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDiscount().getPercent() + ""));
        maxDiscount.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDiscount().getAmount()));
        buttons.setCellValueFactory(param -> new SimpleObjectProperty<>(setChoicePane(param.getValue())));
    }

    @NotNull
    @Contract("_ -> new")
    private Pane setChoicePane(DiscountCode discountCode) {

        Button editButton = new Button("ویرایش اطلاعات");
        Button addAccountButton = new Button("افزودن این کد تحفیف به کاربر");

        HBox hBox = new HBox();

        editButton.setOnAction(event -> {
            ControllerUnit.getInstance().setCurrentDiscountCode(discountCode);
            CreateDiscountCode.setMode(CreateDiscountCode.Mode.Edit);
            MainMenu.change(new CreateDiscountCode().sceneBuilder());
        });
        addAccountButton.setOnAction(event -> {
            ControllerUnit.getInstance().setCurrentDiscountCode(discountCode);
            AccountsList.setMode(AccountsList.Mode.addDiscountCode);
            MainMenu.change(new AccountsList().sceneBuilder());
        });

        hBox.getChildren().addAll(editButton, addAccountButton);
        return new Pane(hBox);
    }
}
