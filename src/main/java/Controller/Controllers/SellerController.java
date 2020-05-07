package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.InvalidCommandException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;
import Model.Tools.ForPend;
import org.w3c.dom.css.DOMImplementationCSS;

import java.util.ArrayList;
import java.util.List;

public class SellerController extends AccountController {
    private ControllerUnit controllerUnit;
    //singleTone
    private static SellerController sellerController;

    private SellerController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public static SellerController getInstance(ControllerUnit controllerUnit) {
        if (sellerController == null) {
            sellerController = new SellerController(controllerUnit);
        }
        return sellerController;
    }

    String sellerUserName = controllerUnit.getAccount().getUserName();

    Seller seller = (Seller) Seller.getAccountByUserName(sellerUserName);

    public CompanyInfo viewCompanyInformation() {
        return seller.getCompanyInfo();
    }

    public List<LogHistory> viewSalesHistory() {
        return seller.getLogHistoryList();
    }

    public List<Product> manageProducts() {
        return seller.getProductList();
    }

    public ProductInfo viewProduct(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        return product.getProductInfo();
    }

    public ArrayList<Customer> viewBuyers(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        //product.getBuyer.....
        return null;

    }

    public void addProductOrOff(ForPend forPend) {
        ///forpend:request--->negah dashtane haraj ya mahsool

    }

    private void newRequest(PendStatus pendStatus) {
    }

    public void removeProduct(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        seller.getProductList().remove(product);
        Product.getProductList().remove(product);
        ;
    }

    public List<Category> showCategories() {
        return Category.getCategoryList();
    }

    public ArrayList<Discount> viewAllOffs() {
        return null;//Discount.getDiscount;
    }

    public ArrayList<Discount> viewOffInFilter() {
        return null; //Discount.Disc;
    }

    public ArrayList<Discount> viewOff(long offId) {
        return null; ///seller.getDiscount;
    }

    public void edit(PendStatus pendable) {
        ///?????
    }

    public double viewBalance() {
        return seller.getBalance();
    }
}
