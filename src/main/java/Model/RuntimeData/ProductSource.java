package Model.RuntimeData;

import Model.Models.Product;

import java.io.File;
import java.util.List;

public class ProductSource {

    private static File productList_File = new File("src/main/resources/allProducts");

    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public ProductSource(List<Product> productList) {
        this.productList = productList;
    }
}
