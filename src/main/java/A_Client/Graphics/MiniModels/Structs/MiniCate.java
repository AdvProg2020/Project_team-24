package A_Client.Graphics.MiniModels.Structs;

public class MiniCate {

    private final String cateId;
    private final String cateName;

    public String getCateName() {
        return cateName;
    }

    public String getCateId() {
        return cateId;
    }

    public MiniCate(String cateId, String cateName) {
        this.cateId = cateId;
        this.cateName = cateName;
    }
}
