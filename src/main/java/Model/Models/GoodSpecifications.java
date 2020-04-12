package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GoodSpecifications implements Packable {

    public long goodSpecificationId;
    private HashMap<String,String> goodSpecifications;

    public HashMap<String, String> getGoodSpecifications() {
        return goodSpecifications;
    }

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(goodSpecificationId, goodSpecifications);
    }

    public GoodSpecifications(long goodSpecificationId, HashMap<String, String> goodSpecifications) {
        this.goodSpecificationId = goodSpecificationId;
        this.goodSpecifications = goodSpecifications;
    }
}
