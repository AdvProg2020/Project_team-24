package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;


import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class ManagerController extends AccountController {
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;
    /****************************************************singleTone***************************************************/
    private static ManagerController managerController;

    private ManagerController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public static ManagerController getInstance(ControllerUnit controllerUnit) {
        if (managerController == null) {
            managerController = new ManagerController(controllerUnit);
        }
        return managerController;
    }

    /**************************************************methods********************************************************/
    public List<Account> viewAllAccounts() {
        return Account.getList();
    }

    public Account viewAccount(String username) throws AccountDoesNotExistException {
        return Account.getAccountByUserName(username);
    }


    public void deleteAccount(String username) throws Exception {
        Account account = Account.getAccountByUserName(username);
        Account.deleteAccount(account);
    }

    public void removeProduct(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        //Product.removeProduct
    }


    public void creatDiscountCode(LocalDate start, LocalDate end, double percent, double maxAmount, int frequentUse)
            throws Exception {
        if (start.isBefore(end) && end.isAfter(LocalDate.now())) {
            DiscountCode discountCode = new DiscountCode();
            DiscountCode.addDiscountCode(discountCode);
            //+m creat disscoiunt code

        } else
            throw new InvalidStartAndEndDateForDiscountCodeException("InvalidStartAndEndDateForDiscountCodeException");


    }

    public List<DiscountCode> viewDiscountCodes() {
        return DiscountCode.getList();
    }

    public DiscountCode viewDiscountCode(long disscoutCodeId) throws DiscountCodeExpiredExcpetion {
        return DiscountCode.getDiscountCodeById(disscoutCodeId);
    }

    public void editDiscountCode(long discountCodeId, String field,String newField) {
        ///check kon ke customer bashe
        Customer customer = (Customer) controllerUnit.getAccount();
        //Field field = DiscountCode.getField;
        //field.set(controllerUnit.getAccount(), newField);

    }

    public void removeDiscountCode(long discountCodeId) throws Exception {
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(discountCodeId);
        DiscountCode.removeFromDiscountCode(discountCode);
    }

    private ArrayList<Customer> findSpecialBuyers() {

        //+m neveshtane yek motheod baraye gereftane hameye customer va barresi 1mil bishter va tashkil list moshtari makhsoos
        return null;
    }

    private void setDiscountCodeToSpecials() {
        //inja miyaym be azaye list bala har koodoom code takhfif mididm/ add to Customer discountcodeList
    }

    public Customer selectRandomBuyer() {
        Random randomAccount = new Random();
        int listSize = Account.getList().size();
        int randomIndex = randomAccount.nextInt(listSize);
        return (Customer) Customer.getList().get(randomIndex);
    }

    private void setDiscountCodeToRandoms(DiscountCode discountCode) {
        selectRandomBuyer().addToDiscountCodeList(discountCode);

    }

    public List<Request> manageRequests() {
        return Request.getList();
    }

    public Request detailsOfRequest(long requestId) throws RequesDoesNotExistException {
        Request request = Request.getRequestById(requestId);
        return request;
    }

    public void acceptRequest(long requestId) throws  RequesDoesNotExistException,Exception{
        Request request = Request.getRequestById(requestId);
        request.acceptRequest();
    }

    public void denyRequest(long requestId) throws Exception {
        Request request = Request.getRequestById(requestId);
        request.declineRequest();
    }

    public List<Category> manageCategories() {
        return Category.getList();
    }

    public void editCategory(String categoryname, String newField) {
        Category category = Category.getCategoryByName(categoryname);
        FieldList fieldList = category.getCategoryField();
        //edit by feild
    }

    public void removeCategory(String categoryName) throws Exception {
        Category category = Category.getCategoryByName(categoryName);
        Category.removeCategory(category);
    }

    public void addCategory(String categoryName) throws Exception {
        Category category = Category.getCategoryByName(categoryName);
        Category.addCategory(category);
    }

    public void createManagerProfileBaseAccount(String username) throws UserNameInvalidException, UserNameTooShortException {
        //BAGHIYE BAKHSH HA HAMOON SIGHN UP
        if (!username.matches("^(\\w+)$")) {
            throw new UserNameInvalidException("UserNameInvalidException");
        } else if (username.toCharArray().length < 6) {
            throw new UserNameTooShortException("UserNameTooShortException");
        }
        Manager manager = new Manager(username);
        Account.addToInRegisteringList(manager);
    }

    public List<Request> showAllRequests() {
        return Request.getList();
    }

    public List<Category> showAllCategories() {
        return Category.getList();
    }
}
