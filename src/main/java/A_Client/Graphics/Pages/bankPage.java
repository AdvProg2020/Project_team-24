package A_Client.Graphics.Pages;
import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Tools.SceneBuilder;
import B_Server.Model.Models.Account;
import B_Server.Model.Models.Accounts.Customer;
import Exceptions.AccountDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Exceptions.SellerDoesNotSellOfThisProduct;
import Structs.MiniAccount;
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
import java.util.ResourceBundle;


public class bankPage implements SceneBuilder, Initializable {
    private final Client client = SendAndReceive.getClient();
    public TextField username;
    public TextField password;
    public Label totelPrice;
    public TextArea description;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("C:\\Users\\ASUS\\IdeaProjects\\Project_team-24\\src\\main\\resources\\Graphics\\BankPage\\bankPage.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public void setTotalPrice(){
        String id = client.getClientInfo().getAccountId();
        try {
            Customer account = (Customer) Account.getAccountById(Long.parseLong(id));
            try {
                Double totalPrice = account.getCart().getTotalPrice();
                totelPrice.setText(String.valueOf(totalPrice));
            } catch (ProductDoesNotExistException e) {
                e.printStackTrace();
            } catch (SellerDoesNotSellOfThisProduct sellerDoesNotSellOfThisProduct) {
                sellerDoesNotSellOfThisProduct.printStackTrace();
            }
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
        }
    }
    public void payButton() {
        List<String> list =  Arrays.asList(username.getText(),password.getText(),"move",totelPrice.toString(),description.getText());
        MiniLogHistory payWithBankAccount = SendAndReceive.Purchase(list);
        PaymentInformation.setLogHistory(payWithBankAccount);
        //...... handle exceptions as alert......

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       MiniAccount account = SendAndReceive.getAccountById(client.getClientInfo().getAccountId());
        username.setText(account.getUsername());
        password.setText(account.getPassword());
    }
}
