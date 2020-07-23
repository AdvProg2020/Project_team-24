package B_Server.Controller.Controllers;

import Exceptions.NotAvailableSortException;
import Exceptions.ProductDoesNotExistException;
import B_Server.Model.Models.Category;
import B_Server.Model.Models.Filter;
import B_Server.Model.Models.Product;
import B_Server.Model.Models.Sorter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsController {

    /******************************************************fields*******************************************************/

    public enum SortElement {

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

    private static ProductsController productsController = new ProductsController();

    private static ControllerUnit controllerUnit = ControllerUnit.getInstance();

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

    public SortElement currentSort() {
        return sortElement;
    }

    public List<Product> showProducts() {

        List<Product> productList = new ArrayList<>(Product.getList());

        List<Filter> filters = FilterController.getInstance().currentFilters();

        filters.sort(Collections.reverseOrder());

        for (Filter filter : filters) {
            productList = productList.stream().filter(filter).collect(Collectors.toList());
        }

        currentSort().getSorter().sorted(productList);

        return productList;
    }

    public List<Product> sort(@NotNull String sortElement) throws NotAvailableSortException {

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
