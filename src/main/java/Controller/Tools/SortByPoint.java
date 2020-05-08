package Controller.Tools;

import Model.Models.Product;

import java.util.Comparator;

public class SortByPoint implements Comparator<Product> {
    public int compare(Product product_a, Product product_b){
        return (int) (product_a.getAverageScore() - product_b.getAverageScore());
    }
}
