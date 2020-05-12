package Controller.Tools;

import Model.Models.Product;

import java.util.Comparator;

public class SortByTime implements Comparator<Product> {
    public int compare(Product product_a, Product product_b) {

//        return product_a.getTimeOfUpload().compareTo(product_b.getTimeOfUpload());
        return 0;
    }

}
