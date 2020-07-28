package A_Client.Graphics.Creates;

import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.MiniProduct;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreatOffer implements SceneBuilder {

    private static MiniProduct miniProduct;
    public TextField start_time;
    public TextField end_time;
    public Button submit_btn;

    public static void setMiniProduct(MiniProduct miniProduct) {
        CreatOffer.miniProduct = miniProduct;
    }

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("C:\\Users\\ASUS\\IdeaProjects\\Project_team-24\\src\\main\\resources\\Graphics\\Offer\\CreateOffer.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public void submit(ActionEvent event) {
        String start = start_time.getText();
        String end = end_time.getText();
        requestForAdd(start,end);

    }
    private void requestForAdd(String start, String end) {
        ArrayList<String> objects = new ArrayList<>();
        objects.add(start);
        objects.add(end);
        objects.add(miniProduct.getProductId() + "");
        objects.add(SendAndReceive.getClient().getClientInfo().getAccountId());

        List<String> answers = SendAndReceive.addOffer(objects);

        if(errorHandler(answers)) goMainMenu();
    }
    private boolean errorHandler(List<String> answers) {
        Matcher matcher = Pattern.compile("^FAIL/(.*)$")
                .matcher(answers.get(2));

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
