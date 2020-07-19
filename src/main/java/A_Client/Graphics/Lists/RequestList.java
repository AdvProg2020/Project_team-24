package A_Client.Graphics.Lists;

import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.MiniRequest;
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
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RequestList implements SceneBuilder, Initializable {

    @FXML
    private TableView<MiniRequest> request_table;
    @FXML
    private TableColumn<MiniRequest, Pane> ar_btn;
    @FXML
    private TableColumn<MiniRequest, String> request_type;
    @FXML
    private TableColumn<MiniRequest, String> request_info;
    @FXML
    private TableColumn<MiniRequest, String> request_mode;
    @FXML
    private TableColumn<MiniRequest, String> request_name;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/RequestList/RequestList.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    private void init() {
        List<MiniRequest> list = SendAndReceive.getAllRequest();
        request_table.setItems(FXCollections.observableList(list));
        request_name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        request_mode.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClass().getSimpleName()));
        request_info.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getInfo()));
        request_type.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getType()));
        ar_btn.setCellValueFactory(param -> new SimpleObjectProperty<>(setChoicePane(param.getValue())));
    }

    @NotNull
    @Contract("_ -> new")
    private Pane setChoicePane(MiniRequest request) {

        HBox hBox = new HBox();

        Button accept = new Button("accept");
        Button decline = new Button("decline");

        accept.setOnAction(event -> {
            SendAndReceive.acceptRequest(request.getId());
            init();
        });

        decline.setOnAction(event -> {
            SendAndReceive.declineRequest(request.getId());
            init();
        });

        hBox.getChildren().addAll(accept, decline);

        return new Pane(hBox);
    }
}
