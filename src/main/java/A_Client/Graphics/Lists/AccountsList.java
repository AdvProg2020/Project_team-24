package A_Client.Graphics.Lists;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Tools.SceneBuilder;
import A_Client.MiniModels.Structs.MiniAccount;
import A_Client.MiniModels.Structs.MiniDiscountCode;
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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AccountsList implements SceneBuilder, Initializable {

    private final Client client = SendAndReceive.getClient();
    private static Mode mode = Mode.Normal;
    public TableView<MiniAccount> accountTableView;
    public TableColumn<MiniAccount, String> username;
    public TableColumn<MiniAccount, Pane> buttons;

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
        List<MiniAccount> list = SendAndReceive.getAllAccounts();
        accountTableView.setItems(FXCollections.observableList(list));
        username.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getUsername()));
        buttons.setCellValueFactory(param -> new SimpleObjectProperty<>(setChoicePane(param.getValue())));
    }

    @NotNull
    @Contract("_ -> new")
    private Pane setChoicePane(MiniAccount account) {

        Button deleteAccount = new Button("حذف کاربر");
        Button addDiscountCode = new Button("افزودن کد تحفیف");

        HBox hBox = new HBox();

        if (account.getAccountT().equals("Manager")) {
            deleteAccount.setDisable(true);
            deleteAccount.setVisible(false);
        }

        deleteAccount.setOnAction(event -> {
            SendAndReceive.DeleteAccountById(
                    account.getAccountId());
            init();
        });

        if (!(client.getClientInfo().getAccountTy().equals("Manager") &&
                account.getAccountT().equals("Customer") &&
                mode == Mode.addDiscountCode)) {
            addDiscountCode.setDisable(true);
            addDiscountCode.setVisible(false);
        }

        addDiscountCode.setOnAction(event -> {
            MiniDiscountCode discountCode = SendAndReceive.getCodeById(client.getClientInfo().getCodeId());
            if (discountCode != null && account.getAccountT().equals("Customer")) {
                SendAndReceive.addToCodesList(Arrays.asList(account.getAccountId(), discountCode.getId()));
//                ((Customer) account).addToDiscountCodeList(discountCode.getId());
//                discountCode.addAccount(account.getId());
//                DataBase.save(discountCode);
//                DataBase.save(account);
            }
            init();
        });
        hBox.getChildren().addAll(deleteAccount, addDiscountCode);
        return new Pane(hBox);
    }

    public enum Mode {
        addDiscountCode, Normal
    }
}
