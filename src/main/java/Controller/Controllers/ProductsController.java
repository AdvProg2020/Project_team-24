package Controller.Controllers;

import Controller.Exceptions.NotAvailableSortException;
import Model.Models.Category;
import Model.Models.Filter;
import Model.Models.Product;

import java.lang.reflect.Field;
import java.security.PublicKey;
import java.util.ArrayList;

public class ProductsController {
    public void products(){}
    public ArrayList<Category> viewCategories(){}
    private void checkDisablefilterIsNotVategory(Filter filter){}
    public void sorting(){}
    public ArrayList<Field> showAvailableSorts(){}
    private void checkSortAvailable()throws NotAvailableSortException {}
    public void sort(Filter availableSort){}
    public void currentSort(){}
    public void disableSort(){}
    public ArrayList<Product> showProducts(){}
    private void checkIfSortChosen(){}
    public ArrayList<Product> showProducts(){}
    public void showProduct(long productId){}
}
