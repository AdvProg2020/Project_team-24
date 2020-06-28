package Graphics.Models;

import Exceptions.AccountDoesNotExistException;
import Exceptions.FieldDoesNotExistException;
import Graphics.MainMenu;
import Graphics.Sprite.SandBoxFX;
import Model.Models.Account;
import Model.Models.Comment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CommentCart implements Initializable {

    private static List<Comment> commentList = new ArrayList<>();
    public Button submit;

    public static void setCommentList(List<Comment> commentList) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (commentList.isEmpty()) return;
        spritePane.getChildren().add(SandBoxFX.sprite());
        Comment comment = commentList.get(0);
        commentList.remove(0);
        init(comment);
    }

    private void init(@NotNull Comment comment) {
        try {
            title.setText(comment.getFieldList().getFieldByName("Title").getString());
            sender.setText(Account.getAccountById(comment.getUserId()).getUserName());
            content.setText(comment.getFieldList().getFieldByName("Content").getString());
        } catch (AccountDoesNotExistException | FieldDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public void submit(ActionEvent event) {

    }
}
