package A_Client.Graphics.Models;
import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Pages.Offer;
import A_Client.Graphics.Pages.Product;
import Structs.MiniAccount;
import Structs.MiniOffer;
import Structs.MiniProduct;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class OfferCart implements Initializable {

    private final Client client = SendAndReceive.getClient();
    private static List<MiniOffer> offerList = SendAndReceive.getAllOffers();
    private MiniOffer offer;

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

    public static void setOfferList(List<MiniOffer> offerList){OfferCart.offerList = offerList;}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (offerList.isEmpty()) {
            mainPane.setVisible(false);
            mainPane.setDisable(true);
            return;
        }
        init();
    }

    public void gotoOffer() {
        setCurrentOffer(offer);
        MainMenu.change(new Offer().sceneBuilder());
        MainMenu.FilterDisable();
    }

    private void setCurrentOffer(MiniOffer miniOffer) {
        SendAndReceive.setCurrentOffer(miniOffer.getOfferId());
        client.getClientInfo().setProductId(miniOffer.getProductId());
    }

    private void init() {
        this.offer = offerList.get(0);
        offerList.remove(0);
        setOfferCart();
    }

    private void setOfferCart() {
        if (offer != null) {
            MiniProduct miniProduct = SendAndReceive.getProductById(offer.getProductId());
            if (miniProduct != null && !miniProduct.getMediasId().equals("0")) setImage(miniProduct.getMediasId());
            setTexts();
        }
    }

    private void setTexts() {
        this.product_name.setText(offer.getProductName());
        MiniAccount accountById = SendAndReceive.getAccountById(String.valueOf(offer.getSellerId()));
        if (accountById != null) {
            String sellerName = accountById.getUsername();
            this.sellerOfProduct.setText(sellerName);
        }
    }

    private void setImage(String mediasId) {
        Image image = SendAndReceive.getImageById(mediasId);
        this.productImage.setImage(image);
    }
}

