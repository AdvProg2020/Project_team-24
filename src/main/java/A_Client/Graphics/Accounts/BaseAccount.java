package A_Client.Graphics.Accounts;

import A_Client.Client.Client;
import A_Client.Client.MessageInterfaces.MessageSupplier;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.MiniModels.Structs.MiniAccount;
import A_Client.JsonHandler.JsonHandler;
import com.gilecode.yagson.YaGson;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseAccount {

    protected final Client client = MainMenu.getClient();
    protected MiniAccount account;
    protected File selectedImage;

    protected void RequestForEdit(String fieldName, String fieldValue) {
        ArrayList<String> objects = new ArrayList<>();
        objects.add(client.getClientInfo().getToken());
        objects.add(fieldName);
        objects.add(fieldValue);
        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.EditFieldOfAccount, objects);
    }

    protected void SettingImage(String first) throws IOException {
        File image = new File(first);
        BufferedImage bufferedImage = ImageIO.read(image);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "Jpg", outputStream);
        String o = new YaGson().toJson(outputStream.toByteArray());
        client.sendAndReceive(MessageSupplier.RequestType.SetImageOfAccount, Collections.singletonList(o));
    }

    protected void logout() {
        client.sendAndReceive(MessageSupplier.RequestType.Logout, Collections.singletonList(client.getClientInfo().getToken()));
    }

    protected void selectFile(ImageView imageView) {
        FileChooser fc = new FileChooser();
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("image", "*.jpg"));
        selectedImage = fc.showOpenDialog(null);
        if (selectedImage == null) return;
        Image image = new Image(selectedImage.toURI().toString());
        imageView.setImage(image);
    }

    protected void ImageInit(ImageView imageView) {
        List<String> answers;
        answers = MainMenu.getClient().sendAndReceive(MessageSupplier.RequestType.GetAccountImage, Collections.singletonList(client.getClientInfo().getToken()));
        imageView.setImage(new JsonHandler<Image>().JsonToObject(answers.get(0), Image.class));
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
