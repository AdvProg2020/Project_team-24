package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.InvalidCommandException;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;

import java.util.ArrayList;

public class SellerController extends AccountController {
    private ControllerUnit controllerUnit;

    public CompanyInfo viewCompanyInformation() {
    }

    public ArrayList<LogHistory> viewSalesHistory() {
    }

    public ArrayList<Product> manageProducts() {
    }

    private void checkInManageProducts() throws InvalidCommandException {
    }

    public ProductInfo viewProduct(long productId) {
        return null;
    }

    public ArrayList<Customer> viewBuyers(long productId) {
    }

    public void addProductOfOff(PendStatus pendable) {
    }

    private void newRequest(PendStatus pendable) {
    }

    public void removeProduct(long productId) {
    }

    public ArrayList<Category> showCategories() {
    }

    public ArrayList<Discount> viewOff() {
    }

    private void checkInViewOff() throws InvalidCommandException {
    }

    public ArrayList<String> viewOff(long offId) {
        return null;
    }

    public void edit(PendStatus pendable) {
    }

    public double viewBalance(Seller seller) {
    }
}
