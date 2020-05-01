package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.InvalidCommandException;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;

import java.util.ArrayList;

public class SellerController extends AccountController {
    private ControllerUnit controllerUnit;

    public CompanyInfo viewCompanyInformation() {return null;
    }

    public ArrayList<LogHistory> viewSalesHistory() {return null;
    }

    public ArrayList<Product> manageProducts() {return null;
    }

    private void checkInManageProducts() throws InvalidCommandException {
    }

    public ProductInfo viewProduct(long productId) {
        return null;
    }

    public ArrayList<Customer> viewBuyers(long productId) {return null;
    }

    public void addProductOfOff(PendStatus pendable) {
    }

    private void newRequest(PendStatus pendable) {
    }

    public void removeProduct(long productId) {
    }

    public ArrayList<Category> showCategories() {return null;
    }

    public ArrayList<Discount> viewOff() {return null;
    }

    private void checkInViewOff() throws InvalidCommandException {
    }

    public ArrayList<String> viewOff(long offId) {
        return null;
    }

    public void edit(PendStatus pendable) {
    }

    public double viewBalance(Seller seller) {return 0;
    }
}
