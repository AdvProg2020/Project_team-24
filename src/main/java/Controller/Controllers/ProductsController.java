package Controller.Controllers;

import Controller.ControllerUnit;
import Controller.Tools.SortByNumberOfVisits;
import Controller.Tools.SortByPoint;
import Controller.Tools.SortByTime;
import Exceptions.NotAvailableSortException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Category;
import Model.Models.FieldList;
import Model.Models.Filter;
import Model.Models.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsController {
    private enum sortElements {
        TIME,
        POINT,
        NUMBEROFVISITS

    }

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
        return Category.getCategoryList();
    }

    public String showAvailableSorts() {
        ////+m time /point/tedad/bazdid

        String availableSorts = "The available sort elements are : Time/Point/NumberOfVisits";
        return availableSorts;
    }

    private void checkSortAvailable(String filter) throws NotAvailableSortException {
        if (!(filter.equals(sortElements.TIME) || filter.equals(sortElements.POINT)) || filter.equals(sortElements.NUMBEROFVISITS)) {
            throw new NotAvailableSortException("NotAvailableSortException");
        }
    }

    public void sort(String sortElement) throws NotAvailableSortException {
        checkSortAvailable(sortElement);
        ///sort ke kardam bayad zakhire konam?
        if (sortElement.equals(sortElements.TIME)) {
            Collections.sort(Product.getProductList(), new SortByTime());
            currentSortEelement = "TIME";


        }
        if (sortElement.equals(sortElements.POINT)) {
            Collections.sort(Product.getProductList(), new SortByPoint());
            currentSortEelement = "POINT";
        }
        if (sortElement.equals(sortElements.NUMBEROFVISITS)) {
            Collections.sort(Product.getProductList(), new SortByNumberOfVisits());
            currentSortEelement = "NUMBEROFVISITS";
        }
    }

    public String currentSort() {
        ///yadoone moteghayer haminja gozashtam oke ? ya na behtare bere tooye model?
        return currentSortEelement;
    }

    public void disableSort() {
        Collections.sort(Product.getProductList(), new SortByNumberOfVisits());
        currentSortEelement = "NUMBEROFVISITS";
    }

    public List<Product> showProducts() {
        return Product.getProductList();
    }

    public String showProduct(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        controllerUnit.setProduct(product);
        return product.toString();

        ///faekr konam behtare be jaye inke man doone doone pas bedam , to string ro yekdamfe bedam...
        //dar natije koore bayad to String shamel tamame vizhegi ha bashe

    }
}
