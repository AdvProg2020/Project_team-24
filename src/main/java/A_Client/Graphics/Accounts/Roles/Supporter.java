package A_Client.Graphics.Accounts.Roles;

import A_Client.ChatClient.ChatRoom;
import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Accounts.BaseAccount;
import A_Client.Graphics.ChatArea.ChatArea;
import A_Client.Graphics.Tools.SceneBuilder;
import Exceptions.FieldDoesNotExistException;
import Structs.MiniAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Supporter extends BaseAccount implements SceneBuilder, Initializable {
    public TextField username;
    public TextField password;
    public BorderPane center;


    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("C:\\Users\\ASUS\\IdeaProjects\\Project_team-24\\src\\main\\resources\\Graphics\\Account\\SupporterAccount.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public void submit() {
        RequestForEdit("password", password.getText());
    }

    public void viewChatroom() {
        center.setCenter(new ChatArea().sceneBuilder().getRoot());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        account = SendAndReceive.getAccountById(client.getClientInfo().getAccountId());
        try {
            password.setText(account.getPersonalInfo().getFieldByName("password").toString());
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }

    }
}
