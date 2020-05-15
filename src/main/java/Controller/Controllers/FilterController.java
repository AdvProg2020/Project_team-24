package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.InvalidFilterException;
import Model.Models.Filter;
import Model.Models.Info;
import Model.Models.Product;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.stream.Stream;

public class FilterController {

    /******************************************************fields*******************************************************/

    private static ControllerUnit controllerUnit = ControllerUnit.getInstance();

    private static List<Product> listOfProductsFiltered = new ArrayList<>(Product.getList());

    private static FilterController filterController = new FilterController();

    private List<Filter> listOfFiltersNowRunning;

    /******************************************************singleTone***************************************************/

    public static FilterController getInstance() {
        return filterController;
    }

    private FilterController() {
    }

    /****************************************************methods********************************************************/

    public List<Field> showAvailableFilters(){
        List<Field> productFieldList = Arrays.asList(Product.class.getFields());
        List<Field> productInfoFieldList = Arrays.asList(Info.class.getFields());
        List<Field> fields = Stream.of(productFieldList, productInfoFieldList).collect(ArrayList::new, List::addAll, List::addAll);
        return fields;
    }
    private void checkFilterValid(Filter filter) throws InvalidFilterException {

        if (!showAvailableFilters().contains(filter)){
            throw new InvalidFilterException("InvalidFilterException");
        }
    }
    public void filter(Filter filter) throws InvalidFilterException {
        checkFilterValid(filter);
       /* if(filter.equals()){List<Product> productsFiltered = Product.getList().stream()
                .filter(p -> p.getAverageScore() > 4).collect(Collectors.toList());
        }

        // for dar list products ... if filter add to listof products
        */
    }

    public List<Filter> currentFilters(){
        return listOfFiltersNowRunning;
    }

    public void disableFilter(Filter filter){
        //?
    }

    public void searchForProduct(Product product , long productId){
        //?
    }
}
