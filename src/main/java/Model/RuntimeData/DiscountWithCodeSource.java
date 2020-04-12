package Model.RuntimeData;

import Model.Models.DiscountWithCode;

import java.io.File;
import java.util.List;

public class DiscountWithCodeSource {

    private static File discountWithCodeList_File = new File("src/main/resources/allDiscountWithCodes");

    private static List<DiscountWithCode> discountWithCodeList;

    static {

    }

    public static List<DiscountWithCode> getDiscountWithCodeList() {
        return discountWithCodeList;
    }
}
