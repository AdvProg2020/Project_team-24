package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.InvalidFilterException;
import Model.Models.Filter;
import Model.Models.Product;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterController {
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;
    private List<Product> listOfProductsFiltered = Product.getList();
    private List<Filter>  listOfFiltersNowRunning ;
    /****************************************************singleTone***************************************************/
    private static FilterController filterController;

    private FilterController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public static FilterController getInstance(ControllerUnit controllerUnit) {
        if (filterController == null) {
            filterController = new FilterController(controllerUnit);
        }
        return filterController;
    }
    /**************************************************methods********************************************************/
    public List<Field> showAvailableFilters(){
        //inke filter ha faghat esme khoe=de field ha bashe
        List<Field> productFieldList = Arrays.asList(Product.class.getFields());
        List<Field> productinfoFieldList = Arrays.asList(ProductInfo.class.getFields());
        List<Field> fields = Stream.of(productFieldList, productinfoFieldList).collect(ArrayList::new, List::addAll, List::addAll);
        //ya inke khodemoon yek mahdoodde filtei specifik besazim??
        return fields;
    }
    private void checkFilterValid(Filter filter) throws InvalidFilterException {
        if (!showAvailableFilters().contains(filter)){
            throw new InvalidFilterException("InvalidFilterException");
        }
    }
    public void filter(Filter filter) throws InvalidFilterException {
        checkFilterValid(filter);
        if(filter.equals()){List<Product> productsFiltered = Product.getList().stream()
                .filter(p -> p.getAverageScore() > 4).collect(Collectors.toList());
        }
        // for dar list products ... if filter add to listof products
    }

    public List<Filter> currentFilters(){return listOfFiltersNowRunning;}
    public void disableFilter(Filter filter){}
    public void searchForProduct(Product product , long productId){}

    //فیلتر کردن بر اساس product va productinfo???/
}
