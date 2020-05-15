package Controller.Controllers;

import Controller.ControllerUnit;
import Controller.Tools.SortByNumberOfVisits;
import Controller.Tools.SortByPoint;
import Controller.Tools.SortByTime;
import Exceptions.NotAvailableSortException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Category;
import Model.Models.Product;
import Model.Models.Sorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsController {

    /******************************************************fields*******************************************************/

    private enum SortElement {

        TIME(), POINT(), NUMBER_OF_VISITS(), DEFAULT();

        Sorter sorter;
        String info;

        SortElement(Sorter sorter, String info) {
            this.sorter = sorter;
            this.info = info;
        }

        public String getInfo() {
            return info;
        }

        public Sorter getSorter() {
            return sorter;
        }
    }

    private SortElement sortElement = SortElement.DEFAULT;

    private static List<Product> productList = new ArrayList<>(Product.getList());

    private static ControllerUnit controllerUnit = ControllerUnit.getInstance();

    private static ProductsController productsController = new ProductsController();

    /****************************************************singleTone***************************************************/

    public static ProductsController getInstance() {
        return productsController;
    }

    private ProductsController() {
    }

    /****************************************************methods********************************************************/

    public List<Category> viewCategories() {
        return Category.getList();
    }

    public String showAvailableSorts() {
        return "The available sort elements are : Time or Point or NumberOfVisits or Default";
    }

    public String currentSort() {
        return sortElement.toString();
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



    public List<Product> disableSort() {
        Collections.sort(productList, new SortByNumberOfVisits());
        currentSortEelement = "NUMBEROFVISITS";
        return productList;
    }

    public List<Product> showProducts() {
        return Product.getList();
    }

    public Product showProduct(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        controllerUnit.setProduct(product);
        return product;
    }
}
