package Model.Models.Structs;

import Exceptions.ProductMediaNotFoundException;
import Model.Models.Data.Data;
import Model.Tools.Packable;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class ProductMedia implements Packable<ProductMedia> {

    private static List<ProductMedia> list;

    private long id;
    private Image image;
    private MediaPlayer player;

    public static ProductMedia getProductMediaById(long id) throws ProductMediaNotFoundException {
        return list.stream()
                .filter(productMedia -> id == productMedia.getId())
                .findFirst()
                .orElseThrow(() -> new ProductMediaNotFoundException("ProductMedia with id:" + id + " not found."));
    }

    public

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    @Override
    public Data<ProductMedia> pack() {
        return new Data<ProductMedia>()
                .addField(id)
                .addField(image)
                .addField(player)
                .setInstance(new ProductMedia());
    }

    @Override
    public ProductMedia dpkg(@NotNull Data<ProductMedia> data) {
        this.id = (long) data.getFields().get(0);
        this.image = (Image) data.getFields().get(1);
        this.player = (MediaPlayer) data.getFields().get(2);
        return this;
    }

    @Override
    public long getId() {
        return id;
    }

    private ProductMedia(String imageAddr, String movieAddr) {
        image = new Image(new File(imageAddr).toURI().toString());
        player = new MediaPlayer(
                new Media(new File(movieAddr).toURI().toString())
        );
    }

    private ProductMedia() {
    }
}
