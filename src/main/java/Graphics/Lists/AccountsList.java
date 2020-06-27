package Graphics.Lists;

import Controller.Controllers.ManagerController;
import Graphics.Tools.SceneBuilder;
import Model.Models.Account;
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

public class AccountsList implements SceneBuilder, Initializable {
    private static final ManagerController managerController = ManagerController.getInstance();
    public TableView<Account> accountTableView;
    public TableColumn<Account, String> username;
    public TableColumn<Account, Pane> deleteAccount;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src\\main\\resources\\Graphics\\ShowAllAccount\\ShowAllAccount.fxml").toURI().toURL());
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
        List<Account> list = managerController.viewAllAccounts();
        accountTableView.setItems(FXCollections.observableList(list));
        username.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getUserName()));
        deleteAccount.setCellValueFactory(param -> new SimpleObjectProperty<Pane>(setChoicePane(param.getValue())));

    }

    @NotNull
    @Contract("_ -> new")
    private Pane setChoicePane(Account account) {

        Button deleteAccount = new Button("حذف کاربر");
        deleteAccount.setOnAction(event -> {
            Account.deleteAccount(account);
            init();
        });
        return new Pane(deleteAccount);
    }

}
