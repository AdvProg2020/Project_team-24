package Controller.Controllers;

import Exceptions.NotAvailableSortException;
import Model.Models.Category;
import Model.Models.Filter;
import Model.Models.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ProductsController {

    public List<Category> viewCategories(){return Category.getCategoryList();}
    private void checkDisablefilterIsNotCategory(Filter filter){}
    public void sorting(){}
    public ArrayList<Field> showAvailableSorts(){return null;}
    private void checkSortAvailable()throws NotAvailableSortException {}
    public void sort(Filter availableSort){}
    public void currentSort(){}
    public void disableSort(){}
    public ArrayList<Product> showProducts(){return null;}
    private void checkIfSortChosen(){}
    public void showProduct(long productId){}
}
