package A_Client.Graphics.Pages;
import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Tools.SceneBuilder;
import B_Server.Model.Models.Account;
import B_Server.Model.Models.Accounts.Customer;
import Exceptions.AccountDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Exceptions.SellerDoesNotSellOfThisProduct;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;

public class bankPage implements SceneBuilder {
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

    public void setTotelPrice(){
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
        String username1 = username.getText();
        String password1 = password.getText();
        String description1 = description.getText();
        SendAndReceive
        SendAndReceive.payWithBankAccount(username1,)
    }
}
