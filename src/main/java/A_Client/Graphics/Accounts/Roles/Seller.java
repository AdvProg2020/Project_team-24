package A_Client.Graphics.Accounts.Roles;

import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Accounts.BaseAccount;
import A_Client.Graphics.Creates.CreateAuction;
import A_Client.Graphics.Creates.CreateCategory;
import A_Client.Graphics.Creates.CreateProduct;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Menus.AuctionsMenu;
import A_Client.Graphics.Menus.LogHistoryMenu;
import A_Client.Graphics.Menus.ProductsMenu;
import Structs.MiniAuction;
import Structs.MiniLogHistory;
import Structs.MiniProduct;
import Structs.MiniProductLog;
import A_Client.Graphics.Models.AuctionCart;
import A_Client.Graphics.Models.LogHistoryCart;
import A_Client.Graphics.Models.ProductCart;
import A_Client.Graphics.Tools.SceneBuilder;
import Exceptions.FieldDoesNotExistException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Seller extends BaseAccount implements SceneBuilder, Initializable {

    public TextField deposit;
    public TextField withdraw;
    @FXML
    private ImageView seller_image;
    @FXML
    private TextField comEmail_txt;
    @FXML
    private TextField comPhone_txt;
    @FXML
    private TextField bran_txt;
    @FXML
    private TextField email_txt;
    @FXML
    private TextField phone_txt;
    @FXML
    private TextField password_txt;
    @FXML
    private TextField username_txt;
    @FXML
    private TextField lName_txt;
    @FXML
    private TextField fName_txt;
    @FXML
    private TableView<MiniProductLog> soldTable;
    @FXML
    private TableColumn<MiniProductLog, String> prices_column;
    @FXML
    private TableColumn<MiniProductLog, String> sold_column;
    @FXML
    private TableColumn<MiniProductLog, String> logId_column;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/Account/SellerAccount.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        init();
    }

    private void init() {
        account = SendAndReceive.getAccountById(client.getClientInfo().getAccountId());
        client.getClientInfo().setMedias_Id(account.getMediasId());

        try {
            username_txt.setText(account.getUsername());
            password_txt.setText(account.getPassword());
            lName_txt.setText(account.getPersonalInfo().getFieldByName("LastName").getString());
            fName_txt.setText(account.getPersonalInfo().getFieldByName("FirstName").getString());
            phone_txt.setText(account.getPersonalInfo().getFieldByName("PhoneNumber").getString());
            email_txt.setText(account.getPersonalInfo().getFieldByName("Email").getString());
            bran_txt.setText(account.getCompanyInfo().getFieldByName("CompanyName").getString());
            comEmail_txt.setText(account.getCompanyInfo().getFieldByName("CompanyEmail").getString());
            comPhone_txt.setText(account.getCompanyInfo().getFieldByName("CompanyPhoneNumber").getString());
//            balance_txt.setText(account.getWallet().getBalance() + "");

            if (!account.getMediasId().equals("0")) ImageInit(seller_image);

            ArrayList<MiniProductLog> miniProductLogs = new ArrayList<>();
            SendAndReceive.GetLogsOfUserById(client.getClientInfo().getAccountId())
                    .forEach(logHistory -> miniProductLogs.addAll(logHistory.getProductLogList()));

            soldTable.setItems(FXCollections.observableArrayList(miniProductLogs));
            logId_column.setCellValueFactory(
                    new PropertyValueFactory<>("productId"));
            sold_column.setCellValueFactory(
                    new PropertyValueFactory<>("productName"));
            prices_column.setCellValueFactory(
                    new PropertyValueFactory<>("finalPrice"));

        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public void selectingImage() {
        super.selectFile(seller_image);
    }

    public void showProducts() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        List<MiniProduct> miniProducts = SendAndReceive.getAllMyProducts();
        setProducts(miniProducts);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    public void showLogHistories() {
        List<MiniLogHistory> logHistoryList = SendAndReceive
                .GetLogsOfUserById(client.getClientInfo().getAccountId());

        LogHistoryMenu.setLogHistoryList(logHistoryList);
        LogHistoryCart.setLogHistoryList(logHistoryList);

        MainMenu.change(new LogHistoryMenu().sceneBuilder());
    }

    public void logout() {
        super.logout();
        back();
    }

    public void submit() {
        if (selectedImage != null) setImage();
        RequestForEdit("password", password_txt.getText());
//        RequestForEdit("balance", balance_txt.getText());
        RequestForEdit("FirstName", fName_txt.getText());
        RequestForEdit("LastName", lName_txt.getText());
        RequestForEdit("Email", email_txt.getText());
        RequestForEdit("PhoneNumber", phone_txt.getText());
        RequestForEdit("CompanyName", bran_txt.getText());
        RequestForEdit("CompanyPhoneNumber", comPhone_txt.getText());
        RequestForEdit("CompanyEmail", comEmail_txt.getText());

        //deposit and withdraw
        SendAndReceive.Deposit(deposit.getText());
        SendAndReceive.WithDraw(withdraw.getText());
        init();
    }

    public void newCategory() {
        CreateCategory.setMode(CreateCategory.Mode.New);
        MainMenu.change(new CreateCategory().sceneBuilder());
    }

    public void newProduct() {
        CreateProduct.setMode(CreateProduct.Mode.New);
        MainMenu.change(new CreateProduct().sceneBuilder());
    }

    public void createAuction() {
        CreateAuction.setMode(CreateAuction.Mode.New);
        MainMenu.change(new CreateAuction().sceneBuilder());
    }

    public void showAuctions() {
        List<MiniAuction> miniAuctions = SendAndReceive.getAllAuctions();
        AuctionsMenu.setList(miniAuctions);
        AuctionCart.setAuctionList(miniAuctions);
        MainMenu.change(new AuctionsMenu().sceneBuilder());
    }

    private void setProducts(List<MiniProduct> list) {
        ProductsMenu.setList(list);
        ProductCart.setProductList(list);
    }
}
