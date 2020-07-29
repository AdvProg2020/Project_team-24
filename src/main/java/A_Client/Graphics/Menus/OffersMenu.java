package A_Client.Graphics.Menus;

import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Models.OfferCart;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.MiniOffer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OffersMenu implements SceneBuilder {
    private static List<MiniOffer> list = new ArrayList<>();
    private static int pageNum = 1;
    public Button next_btn;
    public Button previous_btn;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/Offer/OffersPage.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static void setList(List<MiniOffer> list) {
        OffersMenu.list = list;
    }

    public void next() {
        List<MiniOffer> list = new ArrayList<>();
        for (int i = (++pageNum - 1) * 9; i < pageNum * 9 && i < OffersMenu.list.size(); i++) {
            list.add(OffersMenu.list.get(i));
        }
        checkButtons();
        OfferCart.setOfferList(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    public void previous() {
        List<MiniOffer> list = new ArrayList<>();
        for (int i = (--pageNum - 1) * 9; i < pageNum * 9 && i < OffersMenu.list.size(); i++) {
            list.add(OffersMenu.list.get(i));
        }
        checkButtons();
        OfferCart.setOfferList(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    private void checkButtons() {

        if (pageNum == list.size() / 9 + 1) {
            next_btn.setDisable(true);
            return;
        }
        if (pageNum == 1) {
            previous_btn.setDisable(true);
            return;
        }
        next_btn.setDisable(false);
        previous_btn.setDisable(false);
    }
}
