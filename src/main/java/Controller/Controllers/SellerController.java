package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.ProductDoesNotExistException;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;
import Model.Tools.ForPend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SellerController extends AccountController {
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;
    private Seller seller = (Seller) controllerUnit.getAccount();
    /****************************************************singleTone***************************************************/
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

    /**************************************************methods********************************************************/

    public Info viewCompanyInformation() {
        return seller.getCompanyInfo();
    }

    public List<LogHistory> viewSalesHistory() {
        return seller.getLogHistoryList();
    }

    public List<Product> manageProducts() {
        return seller.getProductList();
    }

    public Info viewProduct(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        return product.getProductInfo();
    }

    public ArrayList<Account> viewBuyers(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        return (ArrayList<Account>) product.getBuyerList();

    }
/*
    public Product addProduct() {
        Product product = new Product();
        FieldList productInfo = (FieldList) Arrays.asList(new SingleString(//hameye vizhegi ha ke fekr mikoni maloome az aval);
        Info pinfo = new Info(product.getClass().getSimpleName(), productInfo, LocalDate.now());
        product.setProductInfo(pinfo);

    }
    public Auction addOff(Info info){
        Auction auction = new Auction();
        FieldList auctionInfo = (FieldList) Arrays.asList(new SingleString(//hameye vizhegi ha ke fekr mikoni maloome az aval);
        Info ainfo = new Info(auction.getClass().getSimpleName(),auctionInfo,LocalDate.now());
        //+m auction.setAuctionIngo(ainfo);

    }
*/
    private  void newRequestForAuction() {
        Request request = new Request();
        //rabt be method bala


    }
    private void newRequestForAddPrpoduct(){
        Request request = new Request();
        //rabt be method bala
    }

    public void removeProduct(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        seller.getProductList().remove(product);
        Product.getList().remove(product);

    }

    public List<Category> showCategories() {
        return Category.getList();
    }

    public List<Auction> viewAllOffs() {
        return Auction.getList();
    }
////in method amle chie??????
    public ArrayList<Auction> viewOffInFilter() {
        return null; //Discount.Disc;
    }

    public Auction viewOff(long offId) {
        return Auction.getAuctionById(offId);
    }

    public void editAuction(String fieldName,String newInfo) {
        //field.set
        //+m Field field = Auction.getClassFieldByName(fieldName);

    }

    public void editProduct(String fieldName,String newInfo) {
        //aval bayad  seller bere tooye safhe product bad biyad diage??
        Product product = controllerUnit.getProduct();
        //+m Field field = product.getClassFieldByName;
    }


    public double viewBalance() {
        return seller.getBalance();
    }
}
