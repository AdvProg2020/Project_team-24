package B_Server.Controller.Controllers;

import B_Server.Controller.Tools.LocalClientInfo;
import Exceptions.InvalidFilterException;
import B_Server.Model.Models.Category;
import Structs.FieldAndFieldList.Field;
import B_Server.Model.Models.Filter;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterController extends LocalClientInfo {

    /******************************************************fields*******************************************************/

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

    public void filter(String filterName, String filterValue) throws InvalidFilterException {
        checkFilterValid(filterName);
        filterList.add(new Filter(filterName,filterValue));
    }

    public List<Filter> currentFilters() {
        return filterList;
    }

    public void disableFilter(String filterName) {
        filterList.removeIf(filter -> filterName.equals(filter.getFieldName()));
    }

    private void checkFilterValid(String filterName) throws InvalidFilterException {

        if (!showAvailableFilters().contains(filterName)) {
            throw new InvalidFilterException(filterName + " is invalid field.");
        }
    }
}
