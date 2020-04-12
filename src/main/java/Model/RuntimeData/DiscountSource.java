package Model.RuntimeData;

import Model.Models.Discount;

import java.io.File;
import java.util.List;

public class DiscountSource {

    private static File discountList_File = new File("src/main/resources/allDiscounts");

    private List<Discount> discountList;

    public List<Discount> getDiscountList() {
        return discountList;
    }

    public DiscountSource(List<Discount> discountList) {
        this.discountList = discountList;
    }
}
