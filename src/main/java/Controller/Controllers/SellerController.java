package Controller.Controllers;

import Exceptions.InvalidCommandException;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;

import java.util.ArrayList;

public class SellerController extends AccountController {

    public CompanyInfo viewCompanyInformation() {
    }

    public ArrayList<LogHistory> viewSalesHistory() {
    }

    public ArrayList<Product> manageProducts() {
    }

    private void checkInManageProducts() throws InvalidCommandException {
    }

    public ProductInfo view(long productId) {
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

    public ArrayList<String> view(long offId) {
    }

    public void edit(PendStatus pendable) {
    }

    public double viewBalance(Seller seller) {
    }
}
