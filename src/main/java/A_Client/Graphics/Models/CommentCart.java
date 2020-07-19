package A_Client.Graphics.Models;

import A_Client.Graphics.Sprite.SandBoxFX;
import Structs.MiniComment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CommentCart implements Initializable {

    private static List<MiniComment> commentList = new ArrayList<>();

    public static void setCommentList(List<MiniComment> commentList) {
        CommentCart.commentList = commentList;
    }

    @FXML
    private Pane spritePane;
    @FXML
    private TextArea content;
    @FXML
    private TextField sender;
    @FXML
    private TextField title;
    @FXML
    private Pane spritePane1;
    @FXML
    private AnchorPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (commentList.isEmpty()) {
            mainPane.setVisible(false);
            mainPane.setDisable(true);
            return;
        }
        ImageView sprite = SandBoxFX.sprite();
        ImageView sprite1 = SandBoxFX.sprite();
        spritePane.getChildren().add(sprite);
        spritePane1.getChildren().add(sprite1);
        MiniComment comment = commentList.get(0);
        commentList.remove(0);
        init(comment);
    }

    private void init(@NotNull MiniComment comment) {
        title.setText(comment.getTitle());
        sender.setText(comment.getSender());
        content.setText(comment.getContent());
    }
}
