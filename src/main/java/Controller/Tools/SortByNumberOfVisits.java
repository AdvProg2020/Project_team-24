package Controller.Tools;

import Model.Models.Product;

import java.util.Comparator;

public class SortByNumberOfVisits implements Comparator<Product> {
    public int compare(Product product_a, Product product_b){
        return (int) (product_a.getNumberOfVisitors() - product_b.getNumberOfVisitors());
    }
}
