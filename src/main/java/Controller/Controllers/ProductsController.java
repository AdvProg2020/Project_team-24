package Controller.Controllers;

import Controller.ControllerUnit;
import Controller.Tools.SortByNumberOfVisits;
import Controller.Tools.SortByPoint;
import Controller.Tools.SortByTime;
import Exceptions.NotAvailableSortException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Category;
import Model.Models.Filter;
import Model.Models.Product;
import org.apache.maven.lifecycle.LifeCyclePluginAnalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsController {
    private enum sortElements {
        TIME,
        POINT,
        NUMBEROFVISITS

    }
    private List<Product> productList = Product.getProductList();

    private String currentSortEelement;

    private ControllerUnit controllerUnit;

    private static ProductsController productsController;

    private ProductsController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public static ProductsController getInstance(ControllerUnit controllerUnit) {
        if (productsController == null) {
            productsController = new ProductsController(controllerUnit);
        }
        return productsController;
    }

    public List<Category> viewCategories() {
        return Category.getList();
    }

    public String showAvailableSorts() {

        String availableSorts = "The available sort elements are : Time/Point/NumberOfVisits";
        return availableSorts;
    }

    private void checkSortAvailable(String filter) throws NotAvailableSortException {
        if (!(filter.equals(sortElements.TIME) || filter.equals(sortElements.POINT)) || filter.equals(sortElements.NUMBEROFVISITS)) {
            throw new NotAvailableSortException("NotAvailableSortException");
        }
    }

    public List<Product> sort(String sortElement) throws NotAvailableSortException {
        checkSortAvailable(sortElement);

        if (sortElement.equals(sortElements.TIME)) {
            Collections.sort(productList , new SortByTime());
            currentSortEelement = "TIME";
        }
        if (sortElement.equals(sortElements.POINT)) {
            Collections.sort(productList , new SortByPoint());
            currentSortEelement = "POINT";
        }
        if (sortElement.equals(sortElements.NUMBEROFVISITS)) {
            Collections.sort(productList, new SortByNumberOfVisits());
            currentSortEelement = "NUMBEROFVISITS";
        }
        return productList;
    }

    public String currentSort() {
        return currentSortEelement;
    }

    public List<Product> disableSort() {
        Collections.sort(productList, new SortByNumberOfVisits());
        currentSortEelement = "NUMBEROFVISITS";
        return productList;
    }

    public List<Product> showProducts() {
        return Product.getProductList();
    }

    public Product showProduct(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        controllerUnit.setProduct(product);
        return product;
    }
}
