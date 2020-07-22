package A_Client.Graphics.Models;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Accounts.Roles.Seller;
import Structs.MiniAccount;
import Structs.MiniProduct;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.List;

public class WageCart {
    private final Client client = SendAndReceive.getClient();
    private static List<MiniProduct> productList = SendAndReceive.getAllMyProducts();
    private MiniProduct product;
    private MiniAccount seller;


    public Label sellerOfProduct;
    public ImageView productImage;
    public Text product_name;
    public Label discount;
    public Text price_ltx;

    private void setImage() {
        Image image = SendAndReceive.getImageById(product.getMediasId());
        this.productImage.setImage(image);
    }
    private void setTexts() {
        this.product_name.setText(product.getProductName());
        this.price_ltx.setText(seller.getUsername());
    }
}
