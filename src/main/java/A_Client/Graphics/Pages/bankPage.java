package A_Client.Graphics.Pages;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.MiniAccount;
import Structs.MiniCart;
import Structs.MiniLogHistory;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class bankPage implements SceneBuilder, Initializable {
    private static MiniCart cart;
    private final static Client client = SendAndReceive.getClient();
    public TextField username;
    public TextField password;
    public Label totalPrice;
    public TextArea description;

    public static void setCart(MiniCart cart) {
        bankPage.cart = cart;
    }

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/BankPage/bankPage.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public void payButton() {
        List<String> list = Arrays.asList(username.getText(), password.getText(),
                "move", totalPrice.toString(), description.getText());

        MiniLogHistory payWithBankAccount = SendAndReceive.Purchase(list);
        PaymentInformation.setLogHistory(payWithBankAccount);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MiniAccount account = SendAndReceive.getAccountById(client.getClientInfo().getAccountId());
        username.setText(Objects.requireNonNull(account).getUsername());
        password.setText(account.getPassword());
        totalPrice.setText(cart.getTotalPrice());
    }
}
