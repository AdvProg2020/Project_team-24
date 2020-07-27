package B_Server.Model.Models.Structs;

import B_Server.Model.DataBase.DataBase;
import Exceptions.ProductMediaNotFoundException;
import B_Server.Model.Models.Data.Data;
import B_Server.Model.Tools.AddingNew;
import B_Server.Model.Tools.Packable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Medias implements Packable<Medias> {

    private static final Object staticLock = new Object();
    private static List<Medias> list;

    private long id;
    private String imageSrc;
    private String mediaSrc;
    private String fileSrc;

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

    // Setter and Getter
    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getMediaSrc() {
        return mediaSrc;
    }

    public void setMediaSrc(String mediaSrc) {
        this.mediaSrc = mediaSrc;
    }

    public String getFileSrc() {
        return fileSrc;
    }

    public void setFileSrc(String fileSrc) {
        this.fileSrc = fileSrc;
    }

    // Override
    @Override
    public Data<Medias> pack() {
        return new Data<Medias>()
                .addField(id)
                .addField(imageSrc)
                .addField(mediaSrc)
                .addField(fileSrc)
                .setInstance(new Medias());
    }

    @Override
    public Medias dpkg(@NotNull Data<Medias> data) {
        this.id = (long) data.getFields().get(0);
        this.imageSrc = (String) data.getFields().get(1);
        this.mediaSrc = (String) data.getFields().get(2);
        this.fileSrc = (String) data.getFields().get(3);
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
