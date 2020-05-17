package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.NotAvailableSortException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Category;
import Model.Models.Product;
import Model.Models.Sorter;

import java.util.ArrayList;
import java.util.List;

public class ProductsController {

    /******************************************************fields*******************************************************/

    private enum SortElement {

        TIME(new Sorter(Sorter.getTimeComparator()),"Sort by upload time"),
        POINT(new Sorter(Sorter.getPointComparator()),"Sort by point"),
        NUMBER_OF_VISITS(new Sorter(Sorter.getVisitorComparator()),"Sort by number of visitors"),
        DEFAULT(new Sorter(Sorter.getDefaultComparator()),"default sort");

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
        return "The available sort elements are : \"Time\" or \"Point\" or \"NumberOfVisits\" or \"Default\"";
    }

    public String currentSort() {
        return sortElement.getInfo();
    }

    public List<Product> showProducts() {
        return Product.getList();
    }

//    private void checkSortAvailable(String filter) throws NotAvailableSortException {
//        if (!(filter.equals("Time") || filter.equals("Point") || filter.equals("NumberOfVisits"))) {
//            throw new NotAvailableSortException("this sort isn't an available Sort.");
//        }
//    }

    public List<Product> sort(String sortElement) throws NotAvailableSortException {
//        checkSortAvailable(sortElement);
        switch (sortElement) {
            case "Time":
                this.sortElement = SortElement.TIME;
                break;
            case "Point":
                this.sortElement = SortElement.POINT;
                break;
            case "NumberOfVisits":
                this.sortElement = SortElement.NUMBER_OF_VISITS;
                break;
            case "Default":
                this.sortElement = SortElement.DEFAULT;
                break;
            default:
                throw new NotAvailableSortException("this sort isn't an available Sort.");
        }
        this.sortElement.getSorter().sorted(productList);
        return productList;
    }

    public List<Product> disableSort() {
        sortElement = SortElement.NUMBER_OF_VISITS;
        return productList;
    }

    public Product showProduct(String productIdString) throws ProductDoesNotExistException , NumberFormatException {
        long id = Long.parseLong(productIdString);
        Product product = Product.getProductById(id);
        controllerUnit.setProduct(product);
        return product;
    }
}
