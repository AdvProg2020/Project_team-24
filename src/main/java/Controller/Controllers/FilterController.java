package Controller.Controllers;

import Controller.Exceptions.InvalidFilterException;
import Model.Models.Filter;
import Model.Models.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterController {
    public void filtering(){}
    public ArrayList<Filter> showAvailableFilters(){}
    public void filter(Filter filter){}
    private void checkFilterValid(Filter filter) throws InvalidFilterException {}
    public ArrayList<Filter> currentFilters(){}
    public void disableFilter(Filter filter){}
    public void searchForProduct(Product product , long productId){}
    public void addfilter(Filter filter){}
    public void removefilter(Filter filter){}
    public void addSortElement(String sortaElement){}
    public void removeSortElement(String sortaElement){}
    public HashMap<String,String> categoryFilter(){}
    public HashMap<String,String> sortElement(){}
}
