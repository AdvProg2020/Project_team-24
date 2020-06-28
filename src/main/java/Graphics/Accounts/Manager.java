package Graphics.Accounts;

import Controller.ControllerUnit;
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
import Model.DataBase.DataBase;
import Model.Models.Structs.Medias;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class Manager implements SceneBuilder, Initializable {

    private static ManagerController managerController = ManagerController.getInstance();
    private Model.Models.Accounts.Manager manager = (Model.Models.Accounts.Manager) ControllerUnit.getInstance().getAccount();
    private File selectedImage;

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

        try {
            UserName.setText(manager.getUserName());
            Password.setText(manager.getPassword());
            LastName.setText(manager.getPersonalInfo().getList().getFieldByName("LastName").getString());
            FirstName.setText(manager.getPersonalInfo().getList().getFieldByName("FirstName").getString());
            PhoneNum.setText(manager.getPersonalInfo().getList().getFieldByName("PhoneNumber").getString());
            Email.setText(manager.getPersonalInfo().getList().getFieldByName("Email").getString());

            if (manager.getMediaId() != 0) {
                manager_image.setImage(Medias.getImage(Medias.getMediasById(manager.getMediaId()).getImageSrc()));
            }
        } catch (FieldDoesNotExistException | ProductMediaNotFoundException e) {
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

            manager.editField("password", Password.getText());
            manager.editField("FirstName", FirstName.getText());
            manager.editField("LastName", LastName.getText());
            manager.editField("Email", Email.getText());
            manager.editField("PhoneNumber", PhoneNum.getText());

        } catch (IOException | ProductMediaNotFoundException | FieldDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    private void setImage() throws IOException, ProductMediaNotFoundException {
        String first = "src/main/resources/DataBase/Images/" + manager.getMediaId() + ".jpg";
        Files.copy(
                selectedImage.toPath(),
                Paths.get(first),
                StandardCopyOption.REPLACE_EXISTING
        );

        Medias medias;
        if (manager.getMediaId() == 0) {
            medias = new Medias();
            Medias.addMedia(medias);
            manager.setMediaId(medias.getId());
        } else {
            medias = Medias.getMediasById(manager.getMediaId());
        }
        medias.setImageSrc(new File(first).toURI().toString());
        DataBase.save(medias);
    }
}
