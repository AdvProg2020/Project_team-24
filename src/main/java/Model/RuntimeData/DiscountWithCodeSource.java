package Model.RuntimeData;

import Model.Models.DiscountWithCode;

import java.io.File;
import java.util.List;

public class DiscountWithCodeSource {

    private static File discountWithCodeList_File = new File("src/main/resources/allDiscountWithCodes");

    private List<DiscountWithCode> discountWithCodeList;

    public List<DiscountWithCode> getDiscountWithCodeList() {
        return discountWithCodeList;
    }

    public DiscountWithCodeSource(List<DiscountWithCode> discountWithCodeList) {
        this.discountWithCodeList = discountWithCodeList;
    }
}
