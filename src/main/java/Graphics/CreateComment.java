package Graphics;

import Exceptions.AccountDoesNotExistException;
import Exceptions.FieldDoesNotExistException;
import Graphics.Models.CommentCart;
import Graphics.Sprite.SandBoxFX;
import Graphics.Tools.SceneBuilder;
import Model.Models.Account;
import Model.Models.Comment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateComment implements Initializable, SceneBuilder {



    private static List<Comment> commentList;
    public Button submit;


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
    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src\\main\\resources\\Graphics\\CreateComment.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public void submit(ActionEvent event) {

    }
}
