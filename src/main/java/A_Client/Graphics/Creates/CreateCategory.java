package A_Client.Graphics.Creates;

import A_Client.Client.Client;
import MessageInterfaces.MessageSupplier;
import A_Client.MiniModels.FieldAndFieldList.Field;
import A_Client.MiniModels.Structs.MiniCate;
import A_Client.JsonHandler.JsonHandler;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Tools.SceneBuilder;
import com.gilecode.yagson.YaGson;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class CreateCategory implements SceneBuilder, Initializable {

    private final Client client = MainMenu.getClient();
    private static Mode mode = Mode.New;
    private List<String> str_feature = new ArrayList<>();
    @FXML
    private MenuButton selected_subCategory;
    @FXML
    private TextField feature;
    @FXML
    private TextField Category_name;
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> feature_column;

    public static void setMode(Mode mode) {
        CreateCategory.mode = mode;
    }

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/CreatePages/CreateCategory/CreateCategory.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init_newMode();
        if (mode == Mode.Edit)
            init_editMode();
    }

    private void init_newMode() {
        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.GetAllCategories, Collections.singletonList(client.getClientInfo().getToken()));
        List<CheckMenuItem> checkMenuItems = new JsonHandler<MiniCate>().JsonsToObjectList(answers, MiniCate.class).stream()
                .map(product -> product.getCateName() + " " + product.getCateId())
                .map(CheckMenuItem::new).collect(Collectors.toList());

        selected_subCategory.getItems().addAll(checkMenuItems);
    }

    private void init_editMode() {
        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.GetCategoryById, Arrays.asList(client.getClientInfo().getToken(), client.getClientInfo().getCateId()));
        MiniCate miniCate = new JsonHandler<MiniCate>().JsonToObject(answers.get(0), MiniCate.class);
        Category_name.setText(miniCate.getCateName());
        List<String> collect = miniCate.getFieldList()
                .getList().stream()
                .map(Field::getFieldName)
                .collect(Collectors.toList());
        setTable(table, feature_column, collect);
    }

    public void submit() {
        String category_name = Category_name.getText();

        if (category_name.isEmpty()) {
            mustBeFilled();
            return;
        }

        List<String> ids = selected_subCategory.getItems().stream()
                .filter(menuItem -> ((CheckMenuItem) menuItem).isSelected())
                .map(menuItem -> {

                    String[] units = menuItem.getText().split(" ");
                    return units[units.length - 1];

                }).collect(Collectors.toList());

        if (mode == Mode.New) {
            addNewCate(category_name, ids);
        }
        if (mode == Mode.Edit) {
            submit_editMode(category_name, ids);
        }

        goMainMenu();
    }

    private void addNewCate(String category_name, List<String> ids) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.add(category_name);
        list.add(new YaGson().toJson(str_feature));
        list.add(new YaGson().toJson(ids));
        client.sendAndReceive(MessageSupplier.RequestType.AddNewCate, list);
    }

    private void submit_editMode(String category_name, @NotNull List<String> ids) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.add(category_name);
        list.add(new YaGson().toJson(ids));
        client.sendAndReceive(MessageSupplier.RequestType.EditFieldOfCate, list);
//        managerController.editCategory(category.getId() + "", "name", category_name);
//        category.setSubCategories(ids.stream().map(Long::parseLong).collect(Collectors.toList()));
//        category.setCategoryFields(new FieldList(str_feature.stream().map(Field::new).collect(Collectors.toList())));
//        DataBase.save(category);
    }

    private void goMainMenu() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    public void addField() {
        String feature = this.feature.getText();
        this.feature.setText("");
        str_feature.add(feature);
        setTable(table, feature_column, str_feature);
    }

    private void mustBeFilled() {
        Tooltip mustFilled = new Tooltip();
        mustFilled.setText("این فیلد را باید پر کنید");
        mustFilled.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        Category_name.setTooltip(mustFilled);
        Category_name.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        failSound();
    }

    private void setTable(TableView<String> table, TableColumn<String, String> features, List<String> featureList) {
        table.setItems(FXCollections.observableList(featureList));
        features.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
    }

    private void failSound() {
        new Thread(() -> new MediaPlayer(
                new Media(
                        new File("src/main/resources/Graphics/SoundEffect/failSound.mp3").toURI().toString()
                )).play()
        ).start();
    }

    public enum Mode {
        Edit, New
    }
}
