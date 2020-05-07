package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.NotAvailableSortException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Category;
import Model.Models.FieldList;
import Model.Models.Filter;
import Model.Models.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ProductsController {

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
        return Category.getCategoryList();
    }

    private void checkDisablefilterIsNotCategory(Filter filter) {
    }

    public String showAvailableSorts() {
        ////time/point/tedad/bazdid
        String availableSorts = "The available sort elements are : Time/Point/NumberOfVisits";
        return availableSorts;
    }

    private void checkSortAvailable(String filter) throws NotAvailableSortException {
        if(!filter.matches("Time|Point|NumberOfVisits")){
            throw new NotAvailableSortException("NotAvailableSortException");
        }
    }

    public void sort(Filter availableSort) throws NotAvailableSortException {
        checkSortAvailable(availableSort.toString());
        //...farayande sort?
    }

    public void currentSort() {
    }

    public void disableSort() {
    }

    public ArrayList<Product> showProducts() {
        return null;
    }

    private void checkIfSortChosen() {
    }

    public void showProduct(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        product.g

    }
}
