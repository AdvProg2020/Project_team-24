package A_Client.Graphics.Pages;

import A_Client.ChatClient.ChatRoom;
import A_Client.ChatClient.YacGram;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.MiniAccount;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;

public class ViewAllSupporters implements SceneBuilder {


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
    private Pane setChoicePane(MiniAccount account) {
        Button chatWithSupporter = new Button("گفت و گو");
        chatWithSupporter.setOnAction(event -> {
            MainMenu.change(new ChatRoom());
        });
    }

}
