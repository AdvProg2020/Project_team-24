package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.InvalidFilterException;
import Model.Models.Category;
import Model.Models.Field.Field;
import Model.Models.Filter;
import Model.Models.Product;


import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterController {

    /******************************************************fields*******************************************************/

    private static ControllerUnit controllerUnit = ControllerUnit.getInstance();

//    private static List<Product> listOfProductsFiltered = new ArrayList<>(Product.getList());

    private static FilterController filterController = new FilterController();

    private List<Filter> filterList = new ArrayList<>();

    /******************************************************singleTone***************************************************/

    public static FilterController getInstance() {
        return filterController;
    }

    private FilterController() {
    }

    /****************************************************methods********************************************************/

    public List<String> showAvailableFilters() {
        List<String> strings = Arrays.asList("ProductName", "CategoryName");
        Category currentCategory = controllerUnit.getCategory();

        if (currentCategory != null)
            strings.addAll(currentCategory.
                    getCategoryFields().
                    getFieldList().
                    stream().
                    map(Field::getFieldName).
                    collect(Collectors.toList()));

        return strings;
    }

    public void filter(String filterName, String filterValue) {
//        checkFilterValid(filterName);
        filterList.add(new Filter(filterName,filterValue));
    }

    public List<Filter> currentFilters() {
        return filterList;
    }

    public void disableFilter(String filterName) {
        filterList.removeIf(filter -> filterName.equals(filter.getFieldName()));
    }

//    private void checkFilterValid(String filterName) throws InvalidFilterException {
//
//        if (!showAvailableFilters().contains(filterName)) {
//            throw new InvalidFilterException(filterName + " is invalid field.");
//        }
//    }
}
