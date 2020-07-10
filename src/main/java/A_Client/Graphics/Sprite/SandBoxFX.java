package A_Client.Graphics.Sprite;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class SandBoxFX {

    private static final Image IMAGE = new Image(new File("src\\main\\java\\Graphics\\Sprite\\sprite_image.jfif").toURI().toString());

    private static final int COLUMNS = 5;
    private static final int COUNT = 10;
    private static final int OFFSET_X = 5;
    private static final int OFFSET_Y = 25;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 55;

    public static @NotNull ImageView sprite() {

        final ImageView imageView = new ImageView(IMAGE);
        imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

        final Animation animation = new SpriteAnimation(
                imageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        return imageView;
    }
}