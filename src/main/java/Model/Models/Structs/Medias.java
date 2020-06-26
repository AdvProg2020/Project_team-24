package Model.Models.Structs;

import Exceptions.ProductMediaNotFoundException;
import Model.DataBase.DataBase;
import Model.Models.Data.Data;
import Model.Tools.Packable;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Medias implements Packable<Medias> {

    private static List<Medias> list;

    private long id;
    private Image image;
    private MediaPlayer player;

    public static Medias getMediasById(long id) throws ProductMediaNotFoundException {
        return list.stream()
                .filter(productMedia -> id == productMedia.getId())
                .findFirst()
                .orElseThrow(() -> new ProductMediaNotFoundException("Medias with id:" + id + " not found."));
    }

    public static void addMedia(Medias medias) {
        list.add(medias);
        DataBase.save(medias,true);
    }

    public static void removeMedia(Medias medias) {
        list.removeIf(productMediaPrime -> productMediaPrime.getId() == medias.getId());
        DataBase.remove(medias);
    }

    // Setter and Getter
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

    // Override
    @Override
    public Data<Medias> pack() {
        return new Data<Medias>()
                .addField(id)
                .addField(image)
                .addField(player)
                .setInstance(new Medias());
    }

    @Override
    public Medias dpkg(@NotNull Data<Medias> data) {
        this.id = (long) data.getFields().get(0);
        this.image = (Image) data.getFields().get(1);
        this.player = (MediaPlayer) data.getFields().get(2);
        return this;
    }

    @Override
    public long getId() {
        return id;
    }

    // Constructor
    public Medias() {
    }
}
