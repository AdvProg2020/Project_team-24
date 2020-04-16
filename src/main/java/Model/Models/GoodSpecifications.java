package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.HashMap;

public class GoodSpecifications implements Packable {

    public long goodSpecificationId;
    private HashMap<String, String> goodSpecifications;

    public HashMap<String, String> getGoodSpecifications() {
        return goodSpecifications;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public GoodSpecifications(long goodSpecificationId, HashMap<String, String> goodSpecifications) {
        this.goodSpecificationId = goodSpecificationId;
        this.goodSpecifications = goodSpecifications;
    }
}
