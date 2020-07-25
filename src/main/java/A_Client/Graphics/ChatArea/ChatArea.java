package A_Client.Graphics.ChatArea;

import A_Client.ChatClient.ChatRoom;
import A_Client.ChatClient.YacGram;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.MiniAccount;
import Toolkit.Connection.Message;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ChatArea implements Initializable, SceneBuilder {

    private static YacGram yacGram;
    private static ChatRoom chatRoom;

    static {
        try {
            MiniAccount miniAccount = SendAndReceive.getAccountById(SendAndReceive.getClient().getClientInfo().getAccountId());
            yacGram = new YacGram(miniAccount, SendAndReceive.getClient().getSocket());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextArea mess_Area;
    @FXML
    private TableView<ChatRoom> contact_table;
    @FXML
    private TableColumn<ChatRoom, Pane> contacts;
    @FXML
    private TableView<Message> chatArea_table;
    @FXML
    private TableColumn<Message, Pane> messages;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        yacGram.online();
        seeMessage();
    }

    public void setChatRoom(int index) {
        chatRoom = yacGram.getChatRoomList().get(index);
    }

    public void submit() {
        chatRoom.write(mess_Area.getText());
        mess_Area.setText("");
    }

    private void seeMessage() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {

            contact_table.setItems(FXCollections.observableList(yacGram.getChatRoomList()));
            chatArea_table.setItems(FXCollections.observableList(chatRoom.getHistory()));

            contacts.setCellValueFactory(param -> new SimpleObjectProperty<>(ContactPane(param.getValue())));
            messages.setCellValueFactory(param -> new SimpleObjectProperty<>(MessagePane(param.getValue(), param.getValue()
                    .getAccountId().equals(yacGram.getMiniAccount().getAccountId()))));

        }));
        timeline.setCycleCount(Integer.MAX_VALUE);
        timeline.play();
    }

    @NotNull
    @Contract("_, _ -> new")
    private Pane MessagePane(@NotNull Message message, boolean me) {

        VBox vBox = new VBox();

        Text u = new Text(message.getUsername());
        Text m = new Text(message.getMessage());

        if (me)
            setAlignment(vBox, u, m, TextAlignment.RIGHT, Pos.CENTER_RIGHT);
        else
            setAlignment(vBox, u, m, TextAlignment.LEFT, Pos.CENTER_LEFT);

        vBox.getChildren().addAll(u,m);

        return new Pane(vBox);
    }

    @NotNull
    @Contract("_ -> new")
    private Pane ContactPane(@NotNull ChatRoom chatRoom) {

        VBox vBox = new VBox();

        List<Message> history = chatRoom.getHistory();
        MiniAccount miniAccount = SendAndReceive.getAccountById(chatRoom.getYarId());

        Text u = new Text(miniAccount.getUsername());
        Text m = new Text(history.get(history.size() - 1).getMessage());
        setAlignment(vBox,u,m, TextAlignment.LEFT, Pos.CENTER_LEFT);

        vBox.getChildren().addAll(u,m);

        return new Pane(vBox);
    }

    private void setAlignment(@NotNull VBox vBox, @NotNull Text u, @NotNull Text m, TextAlignment right, Pos centerRight) {
        u.setTextAlignment(right);
        m.setTextAlignment(right);
        vBox.setAlignment(centerRight);
    }

    public static YacGram getYacGram() {
        return yacGram;
    }


    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("C:\\Users\\ASUS\\IdeaProjects\\Project_team-24\\src\\main\\resources\\Graphics\\QreGram\\YacGram.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
}
