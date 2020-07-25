package A_Client.Graphics.Pages;

import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.ChatArea.ChatArea;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.MiniAccount;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewAllSupporters implements SceneBuilder, Initializable {


    public TableView<MiniAccount> accountTableView;
    public TableColumn<MiniAccount,Pane> buttons;
    public TableColumn<MiniAccount, String> username;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("C:\\Users\\ASUS\\IdeaProjects\\Project_team-24\\src\\main\\resources\\Graphics\\Supporter\\ViewAllSuporters.fxml").toURI().toURL());
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
        List<MiniAccount> list = SendAndReceive.getAllSupporters();
        accountTableView.setItems(FXCollections.observableList(list));
        username.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getUsername()));
        buttons.setCellValueFactory(param -> new SimpleObjectProperty<>(setChoicePane(param.getValue().getUsername())));
    }
    private Pane setChoicePane(String username) {
        Button chatWithSupporter = new Button("گفت و گو");
        chatWithSupporter.setOnAction(event -> {
            ChatArea.getYacGram().join(username);
        });
        return new Pane(chatWithSupporter);
    }

}
