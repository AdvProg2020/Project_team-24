package A_Client.Graphics.Accounts;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.MiniModels.Structs.MiniAccount;
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

    protected void setImage(String first) {
        SendAndReceive.setImageById(client.getClientInfo().getMedias_Id(), new File(first));
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

    protected void back() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    // For setting Image in Server ...
    //        Files.copy(
//                selectedImage.toPath(),
//                Paths.get(first),
//                StandardCopyOption.REPLACE_EXISTING
//        );
//
//        Medias medias;
//        if (customer.getMediaId() == 0) {
//            medias = new Medias();
//            Medias.addMedia(medias);
//            customer.setMediaId(medias.getId());
//        } else {
//            medias = Medias.getMediasById(customer.getMediaId());
//        }
//        medias.setImageSrc(new File(first).toURI().toString());
//        DataBase.save(medias);
}
