package A_Client.Graphics.Menus;

import A_Client.Graphics.Tools.SceneBuilder;
import Structs.MiniOffer;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OffersMenu implements Initializable, SceneBuilder {
    private static List<MiniOffer> list = ProductsMenu.Modes.NormalMode;
    private static int pageNum = 1;
    @Override
    public Scene sceneBuilder(){
        try {
            return FXMLLoader.load(new File("").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
}
