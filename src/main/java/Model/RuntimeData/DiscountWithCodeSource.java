package Model.RuntimeData;

import Model.Models.DiscountWithCode;
import Model.Tools.FileHandler;

import java.io.File;
import java.util.List;

public class DiscountWithCodeSource implements PackClass, FileHandler {

    private static File discountWithCodeList_File = new File("src/main/resources/allDiscountWithCodes");

    private static List<DiscountWithCode> discountWithCodeList;

    static {

    }

    public static List<DiscountWithCode> getDiscountWithCodeList() {
        return discountWithCodeList;
    }
}
