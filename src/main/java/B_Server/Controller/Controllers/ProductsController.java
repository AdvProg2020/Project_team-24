package B_Server.Controller.Controllers;

import B_Server.Controller.Tools.LocalClientInfo;
import Exceptions.NotAvailableSortException;
import B_Server.Model.Models.Filter;
import B_Server.Model.Models.Product;
import B_Server.Model.Models.Sorter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsController extends LocalClientInfo {

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

    private final ThreadLocal<SortElement> sortElement = new ThreadLocal<>();

    private static List<Product> productList = new ArrayList<>(Product.getList());

    private static final ProductsController productsController = new ProductsController();

    /****************************************************singleTone***************************************************/

    public static ProductsController getInstance() {
        return productsController;
    }

    private ProductsController() {
        sortElement.set(SortElement.DEFAULT);
    }

    /****************************************************methods********************************************************/

    public SortElement currentSort() {
        return sortElement.get();
    }

    public List<Product> showProducts() {

        List<Product> productList = new ArrayList<>(Product.getList());

        List<Filter> filters = FilterController.getInstance().currentFilters();

        if (filters != null) {

            if (!filters.isEmpty()) filters.sort(Collections.reverseOrder());

            for (Filter filter : filters) {
                productList = productList.stream().filter(filter).collect(Collectors.toList());
            }
        }

        return productList;
    }

    public void sort(@NotNull String sortElement) throws NotAvailableSortException {

        switch (sortElement) {
            case "Time":
                this.sortElement.set(SortElement.TIME);
                break;
            case "Point":
                this.sortElement.set(SortElement.POINT);
                break;
            case "NumberOfVisits":
                this.sortElement.set(SortElement.NUMBER_OF_VISITS);
                break;
            case "Default":
                this.sortElement.set(SortElement.DEFAULT);
                break;
            default:
                throw new NotAvailableSortException("this sort isn't an available Sort.");
        }
        this.sortElement.get().getSorter().sorted(productList);
    }

    public List<Product> disableSort() {
        sortElement.set(SortElement.NUMBER_OF_VISITS);
        return productList;
    }
}
