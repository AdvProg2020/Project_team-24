package A_Client.Graphics.Accounts;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import Structs.MiniAccount;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.*;
import java.util.Arrays;

public class BaseAccount {

    protected final Client client = SendAndReceive.getClient();
    protected MiniAccount account;
    protected File selectedImage;

    protected void RequestForEdit(String fieldName, String fieldValue) {
        SendAndReceive.EditAccount(Arrays.asList(fieldName, fieldValue));
    }

    protected void setImage() {
        SendAndReceive.setImageById(client.getClientInfo().getAccountId(), selectedImage);
    }

    protected void selectFile(ImageView imageView) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter ext = new FileChooser
                .ExtensionFilter("image", "*.jpg");

        fc.setSelectedExtensionFilter(ext);
        selectedImage = fc.showOpenDialog(null);

        if (selectedImage != null) {
            Image image = new Image(selectedImage.toURI().toString());
            imageView.setImage(image);
        }
    }

    protected void ImageInit(ImageView imageView) {
        imageView.setImage(SendAndReceive.getImageById(client.getClientInfo().getMedias_Id()));
    }

    protected void logout() {
        SendAndReceive.Logout();
    }

    @FXML
    protected void back() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }
}
