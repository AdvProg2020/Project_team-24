package Graphics.Lists;

import Controller.Controllers.ManagerController;
import Exceptions.AccountDoesNotExistException;
import Graphics.Tools.SceneBuilder;
import Model.Models.Auction;
import Model.Models.Product;
import Model.Models.Request;
import Model.Tools.ForPend;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private static ManagerController managerController = ManagerController.getInstance();

    public TableView<Request> request_table;
    public TableColumn<Request, Pane> ar_btn;
    public TableColumn<Request, String> request_type;
    public TableColumn<Request, String> request_info;
    public TableColumn<Request, String> request_mode;
    public TableColumn<Request, String> request_name;

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
        List<Request> list = managerController.showAllRequests();
        request_table.setItems(FXCollections.observableList(list));
        request_name.setCellValueFactory(param -> {
            ForPend forPend = param.getValue().getForPend();
            return new SimpleStringProperty(forPend instanceof Product ? ((Product) forPend).getName() : ((Auction) forPend).getName());
        });
        request_mode.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClass().getSimpleName()));
        request_info.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getInformation()));
        request_type.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTypeOfRequest()));
        ar_btn.setCellValueFactory(param -> new SimpleObjectProperty<>(setChoicePane(param.getValue())));
    }

    @NotNull
    @Contract("_ -> new")
    private Pane setChoicePane(Request request) {

        HBox hBox = new HBox();

        Button accept = new Button("accept");
        Button decline = new Button("decline");

        accept.setOnAction(event -> {
            try {
                request.acceptRequest();
            } catch (AccountDoesNotExistException e) {
                e.printStackTrace();
            }
            init();
        });
        decline.setOnAction(event -> {
            try {
                request.declineRequest();
            } catch (AccountDoesNotExistException e) {
                e.printStackTrace();
            }
            init();
        });

        hBox.getChildren().addAll(accept,decline);

        return new Pane(hBox);
    }
 }
