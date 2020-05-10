package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.ProductDoesNotExistException;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;
import Model.Tools.ForPend;

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

    Seller seller = (Seller) controllerUnit.getAccount();

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
        //manager accept kard add mishe
        ///forpend:request--->negah dashtane haraj ya mahsool
        if(forPend instanceof Product){
            //+m creat product
        }
        if(forPend instanceof Auction){
            //+m creat auction
        }

    }

    private  void newRequestForAuction() {

    }
    private void newRequestForAddPrpoduct(){

    }

    public void removeProduct(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        seller.getProductList().remove(product);
        Product.getProductList().remove(product);
        ;
    }

    public List<Category> showCategories() {
        return Category.getList();
    }

    public ArrayList<Auction> viewAllOffs() {
        return null;//Discount.getDiscount;
    }

    public ArrayList<Auction> viewOffInFilter() {
        return null; //Discount.Disc;
    }

    public ArrayList<Discount> viewOff(long offId) {
        return null; ///seller.getDiscount;
    }

    public void editAuction(String fieldName,String newInfo) {
        //field.set
        Field field = Auction.getFieldByName(fieldName);

    }

    public void editProduct(String fieldName,String newInfo) {
        //field.set
        Field field = Product.getFieldByName(fieldName);
    }


    public double viewBalance() {
        return seller.getBalance();
    }
}
