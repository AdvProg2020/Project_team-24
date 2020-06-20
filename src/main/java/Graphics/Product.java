package Graphics;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class Product implements Initializable {

    private static Stack<Model.Models.Product> productStack = new Stack<>();
    private Model.Models.Product product;

    @FXML
    private TableColumn<?, ?> ProductInfoName;
    @FXML
    private TableColumn<?, ?> ProductInfoValue;
    @FXML
    private TableColumn<?, ?> CategoryInfoName;
    @FXML
    private TableColumn<?, ?> CategoryInfo;
    @FXML
    private Tab comment;
    @FXML
    private Pane CommentCart;
    @FXML
    private Pane CommentCart1;
    @FXML
    private Pane CommentCart2;
    @FXML
    private Button newComment;
    @FXML
    private AnchorPane feature;
    @FXML
    private Label productName;
    @FXML
    private Pane stars;
    @FXML
    private ImageView star1;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star3;
    @FXML
    private ImageView star4;
    @FXML
    private ImageView star5;
    @FXML
    private Label price;
    @FXML
    private MediaView productVideo;
    @FXML
    private Button videoButton;
    @FXML
    private TextField searchBar;
    @FXML
    private ImageView productImage;
    @FXML
    private Pane similar1;
    @FXML
    private Pane similar2;
    @FXML
    private Pane similar3;
    @FXML
    private Pane similar4;
    @FXML
    private Pane similar5;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        product = productStack.lastElement();
        setImages();
        setMedias();
        similarPr();
    }

    public void newComment() {
    }

    public void playVideo() {
    }

    public void compare() {
    }

    public void openCart() {
    }

    private void setImages() {

        productImage.setImage(product.getProductImage());

        Image filledStar = new Image(new File("src/main/resources/Graphics/Product/icons8-star-filled-16.png").toURI().toString());

        switch ((int) product.getAverageScore()) {
            case 5:
                star5.setImage(filledStar);
            case 4:
                star4.setImage(filledStar);
            case 3:
                star3.setImage(filledStar);
            case 2:
                star2.setImage(filledStar);
            case 1:
                star1.setImage(filledStar);
        }

    }

    private void setMedias() {
        productVideo = new MediaView(new MediaPlayer(product.getProductMedia()));
    }

    private void similarPr() {
        similar1.set
    }
}
