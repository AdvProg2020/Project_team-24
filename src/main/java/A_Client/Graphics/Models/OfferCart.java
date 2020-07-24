package A_Client.Graphics.Models;
import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Pages.Product;
import Structs.MiniOffer;
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
        MainMenu.change(new Product().sceneBuilder());
        MainMenu.FilterDisable();
    }

    private void setCurrentOffer(MiniOffer miniOffer) {
        SendAndReceive.setCurrentOffer(miniOffer.getProductId());
        client.getClientInfo().setProductId(miniOffer.getProductId());
    }


    private void init() {
        this.offer = offerList.get(0);
        offerList.remove(0);
        setOfferCart();
    }

    private void setOfferCart() {
        if (offer != null) {
            if (offer.getMediasId() != null) setImage();
            setTexts();
        }
    }

    private void setTexts() {
        this.product_name.setText(offer.getProductName());
        String sellerName = SendAndReceive.getAccountById(String.valueOf(offer.getSellerId())).getUsername();
        this.sellerOfProduct.setText(sellerName);
    }

    private void setImage() {
        Image image = SendAndReceive.getImageById(offer.getMediasId());
        this.productImage.setImage(image);
    }

}

