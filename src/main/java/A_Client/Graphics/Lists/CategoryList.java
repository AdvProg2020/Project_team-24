package A_Client.Graphics.Lists;

import A_Client.Graphics.Accounts.Manager;
import A_Client.Graphics.Creates.CreateCategory;
import A_Client.Graphics.Tools.SceneBuilder;
import B_Server.Controller.ControllerUnit;
import B_Server.Controller.Controllers.SellerController;
import A_Client.Graphics.MainMenu;
import B_Server.Model.Models.Category;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryList implements SceneBuilder, Initializable {
    private static SellerController sellerController = SellerController.getInstance();
    public TableView<Category> CategoryList;
    public TableColumn<Category,String> categoryName;
    public TableColumn<Category,Pane> editCategory;

    public void back() {
        MainMenu.change(new Manager().sceneBuilder());
    }

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/CategoryList/CategoryList.fxml").toURI().toURL());
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
        List<Category> list = sellerController.showCategories();
        CategoryList.setItems(FXCollections.observableList(list));
        categoryName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        editCategory.setCellValueFactory(param -> new SimpleObjectProperty<Pane>(setChoicePane(param.getValue())));
    }

    @NotNull
    @Contract("_ -> new")
    private Pane setChoicePane(Category category) {
        Button editCategory = new Button("ویرایش دسته");
        editCategory.setOnAction(event -> {
            CreateCategory.setMode(CreateCategory.Mode.Edit);
            ControllerUnit.getInstance().setCategory(category);
            MainMenu.change(new CreateCategory().sceneBuilder());
            init();
        });
        return new Pane(editCategory);
    }
}