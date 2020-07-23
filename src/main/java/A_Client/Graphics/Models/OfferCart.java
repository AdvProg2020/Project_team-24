package A_Client.Graphics.Models;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import Structs.MiniAccount;
import Structs.MiniOffer;
import Structs.MiniProduct;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OfferCart implements Initializable {
    private final Client client = SendAndReceive.getClient();
    private static List<MiniOffer> offerList = SendAndReceive.getAllOffers();
    private MiniProduct product;
    private int sellerIndex;

    @FXML
    public Label sellerOfProduct;
    @FXML
    public ImageView productImage;
    @FXML
    public Text product_name;
    @FXML
    public Label discount;
    @FXML
    public Text price_ltx;
    @FXML
    public AnchorPane mainPane;

    public static void setOfferList(List<MiniOffer> offerList){OfferCart.offerList = offerList}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (offerList.isEmpty()) {
            mainPane.setVisible(false);
            mainPane.setDisable(true);
            return;
        }
        init();
    }
    private void init() {

    }
}
