package Controller.Controllers;

import Controller.Controllers.AccountController;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Category;
import Model.Models.Discount;
import Model.Models.Request;
import Model.Models.Roles.Buyer;

import java.util.ArrayList;

public class ManagerController extends AccountController {
    private void checkInManageUsers() throws invalidCommandException{}
    public ArrayList<Account> view(String username){}
    public void delete(String username){}
    public void creatManageProfile() throws creatManagerException{}
    private void checkInManageAllProducts() throws invalidCommandException{}
    public void remove(long productId){}
    private void checkIdExistance(long productId) throws invalidIdException{}
    public void creatDiscountCode(){}
    private void checkDisscountInfoValid(Discount discountInfo) throws invalidCommandException{}
    public ArrayList<Discount> viewDiscountCodes(){}
    public void viewDiscountCode(long disscoutCodeId){}
    public void editDiscountCode(long discountCodeId){}
    public void removeDiscountCode(long discountCodeId){}
    private ArrayList<Customer> findSpecialBuyers(){}
    private void setDiscountCodeToSpecials(){}
    public ArrayList<Customer> selectRandomBuyers(){}
    private void setDiscountCodeToRandoms(){}
    public ArrayList<Request> manageRequests(){}
    public boolean requestAccepted(long registerId , String acceptorDecline){}
    public ArrayList<Category> manageCategories(){}
    public void editCategory(Category category){}
    public void removeCategory(Category category){}


        }
