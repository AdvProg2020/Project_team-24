package Model.RuntimeData;

import Model.Models.Product;

import java.io.File;
import java.util.List;

public class ProductSource {

    private static File productList_File = new File("src/main/resources/allProducts");

    private static List<Product> productList;

    static {

    }

    public static List<Product> getProductList() {
        return productList;
    }
}
