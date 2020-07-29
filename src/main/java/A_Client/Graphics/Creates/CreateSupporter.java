package A_Client.Graphics.Creates;

import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.FieldAndFieldList.Field;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateSupporter implements SceneBuilder {
    public TextField username;
    public TextField password;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/Supporter/createSupporter.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public void createSupporter() {
        String username = this.username.getText();
        String password = this.password.getText();
        List<String> fields = new ArrayList<>();
        fields.add(username);
        fields.add(password);
        String answer = SendAndReceive.addAccount(fields, "Supporter");

        if(errorHandler(answer)) goMainMenu();
    }

    private boolean errorHandler(String answer) {
        Matcher matcher = Pattern.compile("^FAIL/(.*)$")
                .matcher(answer);

        if (matcher.find()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(matcher.group(1));
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private void goMainMenu() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

}
