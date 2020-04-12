package Model.RuntimeData;

import Model.Models.Discount;

import java.io.File;
import java.util.List;

public class DiscountSource {

    private static File discountList_File = new File("src/main/resources/allDiscounts");

    private static List<Discount> discountList;

    static {

    }

    public static List<Discount> getDiscountList() {
        return discountList;
    }
}
