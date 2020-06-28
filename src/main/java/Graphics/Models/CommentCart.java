package Graphics.Models;

import Controller.ControllerUnit;
import Controller.Controllers.BuyerController;
import Controller.Controllers.ProductController;
import Controller.Controllers.ProductsController;
import Exceptions.AccountDoesNotExistException;
import Exceptions.CannotRateException;
import Exceptions.FieldDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Graphics.MainMenu;
import Graphics.Sprite.SandBoxFX;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Comment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CommentCart implements Initializable {
    private static List<Comment> commentList = new ArrayList<>();
    public Button submit;
    public ProductController productController = ProductController.getInstance();

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
        ImageView sprite = SandBoxFX.sprite();
        spritePane.getChildren().add(sprite);
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

    public void submit() {

        reset_btn();

        if (ControllerUnit.getInstance().getAccount() instanceof Customer)
            try {
                productController.addComment(title.getText(), content.getText());
            } catch (ProductDoesNotExistException | CannotRateException e) {
                e.printStackTrace();
            }
        else commentError();

    }

    private void reset_btn() {
        submit.setStyle("-fx-border-color: #eeba00;");
    }

    private void commentError() {
        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("نمی توانید کامنت بگذارید");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        submit.setTooltip(toolTip_username);
        submit.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
    }


}
