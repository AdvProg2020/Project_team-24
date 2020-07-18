package A_Client.Graphics.Accounts;

import MessageInterfaces.MessageSupplier;
import A_Client.Graphics.MiniModels.Structs.MiniAccount;
import A_Client.Graphics.Pages.SignUp;
import A_Client.JsonHandler.JsonHandler;
import Exceptions.FieldDoesNotExistException;
import A_Client.Graphics.Creates.CreateCategory;
import A_Client.Graphics.Creates.CreateDiscountCode;
import A_Client.Graphics.Lists.AccountsList;
import A_Client.Graphics.Lists.CategoryList;
import A_Client.Graphics.Lists.DiscountCodeList;
import A_Client.Graphics.Lists.RequestList;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Menus.AuctionsMenu;
import A_Client.Graphics.Tools.SceneBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Manager extends BaseAccount implements SceneBuilder, Initializable {

    @FXML
    private ImageView manager_image;
    @FXML
    private TextField Email;
    @FXML
    private TextField UserName;
    @FXML
    private TextField Password;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField LastName;
    @FXML
    private TextField PhoneNum;

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/Account/ManagerAccount.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.GetMiniAccount, Collections.singletonList(client.getClientInfo().getToken()));
        account = new JsonHandler<MiniAccount>().JsonToObject(answers.get(0), MiniAccount.class);

        try {
            UserName.setText(account.getUsername());
            Password.setText(account.getPassword());
            LastName.setText(account.getPersonalInfo().getFieldByName("LastName").getString());
            FirstName.setText(account.getPersonalInfo().getFieldByName("FirstName").getString());
            PhoneNum.setText(account.getPersonalInfo().getFieldByName("PhoneNumber").getString());
            Email.setText(account.getPersonalInfo().getFieldByName("Email").getString());

            if (account.getMediasId() == null) {
                answers = MainMenu.getClient().sendAndReceive(MessageSupplier.RequestType.GetAccountImage, Collections.singletonList(client.getClientInfo().getToken()));
                manager_image.setImage(new JsonHandler<Image>().JsonToObject(answers.get(0), Image.class));
            }

        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public void ChoosePhoto() {
        FileChooser fc = new FileChooser();
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("image", "*.jpg", "*.png"));
        selectedImage = fc.showOpenDialog(null);
        Image value = new Image(selectedImage.toURI().toString());
        manager_image.setImage(value);
    }

    public void AddCategory() {
        CreateCategory.setMode(CreateCategory.Mode.New);
        MainMenu.change(new CreateCategory().sceneBuilder());
    }

    public void CategoryList() {
        MainMenu.change(new CategoryList().sceneBuilder());
    }

    public void UserList() {
        AccountsList.setMode(AccountsList.Mode.Normal);
        MainMenu.change(new AccountsList().sceneBuilder());
    }

    public void CreateManager() {
        SignUp.setMode(SignUp.Mode.ManagerMode);
        MainMenu.change(new SignUp().sceneBuilder());
    }

    public void CreateDiscountCode() {
        CreateDiscountCode.setMode(CreateDiscountCode.Mode.New);
        MainMenu.change(new CreateDiscountCode().sceneBuilder());
    }

    public void DiscountCodeList() {
        MainMenu.change(new DiscountCodeList().sceneBuilder());
    }

    public void BackToMainMenu() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    public void requestList() {
        MainMenu.change(new RequestList().sceneBuilder());
    }

    public void submit() {

        try {

            if (selectedImage != null) {
                setImage();
            }

            RequestForEdit("password", Password.getText());
            RequestForEdit("FirstName", FirstName.getText());
            RequestForEdit("LastName", LastName.getText());
            RequestForEdit("Email", Email.getText());
            RequestForEdit("PhoneNumber", PhoneNum.getText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setImage() throws IOException {
        String first = "src/main/resources/DataBase/Images/" + account.getMediasId() + ".jpg";
        super.SettingImage(first);
    }

    public void viewAuctions() {
        MainMenu.change(new AuctionsMenu().sceneBuilder());
    }
}
