package Graphics.Accounts;

import Controller.ControllerUnit;
import Controller.Controllers.BuyerController;
import Controller.Controllers.ManagerController;
import Exceptions.FieldDoesNotExistException;
import Exceptions.ProductMediaNotFoundException;
import Graphics.Creates.CreateCategory;
import Graphics.Creates.CreateDiscountCode;
import Graphics.Lists.AccountsList;
import Graphics.Lists.CategoryList;
import Graphics.Lists.DiscountCodeList;
import Graphics.Lists.RequestList;
import Graphics.MainMenu;
import Graphics.SignUp;
import Graphics.Tools.SceneBuilder;
import Model.Models.Structs.Medias;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import sun.applet.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Manager implements SceneBuilder, Initializable {

    private static ManagerController managerController = ManagerController.getInstance();
    public ImageView product_image;
    private Model.Models.Accounts.Manager manager = (Model.Models.Accounts.Manager) ControllerUnit.getInstance().getAccount();
    private File selectedImage;
    private FileChooser fc = new FileChooser();

    @FXML
    private TextField Email;
    @FXML
    private Button back;
    @FXML
    private Button viewOffs;
    @FXML
    private Button editOff;
    @FXML
    private Button newOff;
    @FXML
    private Button creatManager;
    @FXML
    private Button viewAccounts;
    @FXML
    private Button deleteAccount;
    @FXML
    private Button viewCategories;
    @FXML
    private Button editCategories;
    @FXML
    private Button creatCategory;
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
        UserName.setText(manager.getUserName());
        Password.setText(manager.getPassword());
        try {
            LastName.setText(manager.getPersonalInfo().getList().getFieldByName("LastName").getString());
            FirstName.setText(manager.getPersonalInfo().getList().getFieldByName("FirstName").getString());
            PhoneNum.setText(manager.getPersonalInfo().getList().getFieldByName("PhoneNumber").getString());
            Email.setText(manager.getPersonalInfo().getList().getFieldByName("Email").getString());
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }
        if (manager.getMediaId() != 0) {
            try {
                product_image.setImage(Medias.getImage(Medias.getMediasById(manager.getMediaId()).getImageSrc()));
            } catch (ProductMediaNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void ChoosePhoto() {
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("image", "*.jpg", "*.png"));
        selectedImage = fc.showOpenDialog(null);
        Image value = new Image(selectedImage.toURI().toString());
        product_image.setImage(value);
    }

    public void AddCategory(ActionEvent event) {
        MainMenu.change(new CreateCategory().sceneBuilder());
    }

    public void CategoryList(ActionEvent event) {
        MainMenu.change(new CategoryList().sceneBuilder());
    }


    public void UserList(ActionEvent event) {
        MainMenu.change(new AccountsList().sceneBuilder());
    }

    public void CreateManager(ActionEvent event) {
        MainMenu.change(new SignUp().sceneBuilder());
    }

    public void CreateDiscountCode(ActionEvent event) {
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
}
