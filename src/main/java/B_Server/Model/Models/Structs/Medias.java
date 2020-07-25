package B_Server.Model.Models.Structs;

import B_Server.Model.DataBase.DataBase;
import Exceptions.ProductMediaNotFoundException;
import B_Server.Model.Models.Data.Data;
import B_Server.Model.Tools.AddingNew;
import B_Server.Model.Tools.Packable;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Medias implements Packable<Medias> {

    private static final Object staticLock = new Object();
    private static List<Medias> list;

    private long id;
    private String imageSrc;
    private String playerSrc;

    public static void setList(List<Medias> list) {
        Medias.list = list;
    }

    public static Medias getMediasById(long id) throws ProductMediaNotFoundException {
        return list.stream()
                .filter(productMedia -> id == productMedia.getId())
                .findFirst()
                .orElseThrow(() -> new ProductMediaNotFoundException("Medias with the id:" + id + " not found."));
    }

    public static void addMedia(@NotNull Medias medias) {
        synchronized (staticLock) {
            medias.setId(AddingNew.getRegisteringId().apply(list));
            list.add(medias);
            DataBase.save(medias, true);
        }
    }

    public static void removeMedia(Medias medias) {
        list.removeIf(productMediaPrime -> productMediaPrime.getId() == medias.getId());
        DataBase.remove(medias);
    }

    // Others
    @NotNull
    @Contract("_ -> new")
    public static Image getImage(String src) {
        return new Image(src);
    }

    @NotNull
    @Contract("_ -> new")
    public static MediaPlayer getMediaPlayer(String src) {
        return new MediaPlayer(new Media(src));
    }

    // Setter and Getter
    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getPlayerSrc() {
        return playerSrc;
    }

    public void setPlayerSrc(String playerSrc) {
        this.playerSrc = playerSrc;
    }

    // Override
    @Override
    public Data<Medias> pack() {
        return new Data<Medias>()
                .addField(id)
                .addField(imageSrc)
                .addField(playerSrc)
                .setInstance(new Medias());
    }

    @Override
    public Medias dpkg(@NotNull Data<Medias> data) {
        this.id = (long) data.getFields().get(0);
        this.imageSrc = (String) data.getFields().get(1);
        this.playerSrc = (String) data.getFields().get(2);
        return this;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // Constructor
    public Medias() {
    }
}
