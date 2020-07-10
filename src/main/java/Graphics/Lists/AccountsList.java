package Graphics.Lists;

import Controller.ControllerUnit;
import Controller.Controllers.ManagerController;
import Exceptions.AccountDoesNotExistException;
import Graphics.Tools.SceneBuilder;
import Model.DataBase.DataBase;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.DiscountCode;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
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
    private static Mode mode = Mode.Normal;
    public TableView<Account> accountTableView;
    public TableColumn<Account, String> username;
    public TableColumn<Account, Pane>  buttons;

    public static void setMode(Mode mode) {
        AccountsList.mode = mode;
    }

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
        buttons.setCellValueFactory(param -> new SimpleObjectProperty<>(setChoicePane(param.getValue())));
    }

    @NotNull
    @Contract("_ -> new")
    private Pane setChoicePane(Account account) {

        Button deleteAccount = new Button("حذف کاربر");
        Button addDiscountCode = new Button("افزودن کد تحفیف");

        HBox hBox = new HBox();

        if (account instanceof Manager) {
            deleteAccount.setDisable(true);
            deleteAccount.setVisible(false);
        }

        deleteAccount.setOnAction(event -> {
            try {
                managerController.deleteAccount(account.getUserName());
            } catch (AccountDoesNotExistException e) {
                e.printStackTrace();
            }
            init();
        });

        if (!(ControllerUnit.getInstance().getAccount() instanceof Manager &&
                account instanceof Customer &&
                mode == Mode.addDiscountCode)) {
            addDiscountCode.setDisable(true);
            addDiscountCode.setVisible(false);
        }

        addDiscountCode.setOnAction(event -> {
            DiscountCode discountCode = ControllerUnit.getInstance().getCurrentDiscountCode();
            if (discountCode != null && account instanceof Customer) {
                ((Customer) account).addToDiscountCodeList(discountCode.getId());
                discountCode.addAccount(account.getId());
                DataBase.save(discountCode);
                DataBase.save(account);
            }
            init();
        });
        hBox.getChildren().addAll(deleteAccount,addDiscountCode);
        return new Pane(hBox);
    }

    public enum Mode {
        addDiscountCode,Normal
    }
}
