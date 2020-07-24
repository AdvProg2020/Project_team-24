package A_Client.Graphics.Creates;

import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.FieldAndFieldList.Field;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateSupporter implements SceneBuilder {
    public TextField username;
    public TextField password;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("C:\\Users\\ASUS\\IdeaProjects\\Project_team-24\\src\\main\\resources\\Graphics\\Supporter\\createSupporter.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public void createSupporter(ActionEvent event) {
        String username1 = username.getText();
        String password1 = password.getText();
        List<String> fields = new ArrayList<>();
        fields.add(username1);
        fields.add(password1);
        SendAndReceive.addAccount(fields,"Supporter");
    }
}
