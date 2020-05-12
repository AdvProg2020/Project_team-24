package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManagerController extends AccountController {

    /******************************************************fields*******************************************************/

    private static ControllerUnit controllerUnit = ControllerUnit.getInstance();

    private static ManagerController managerController = new ManagerController();

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /******************************************************singleTone***************************************************/

    public static ManagerController getInstance() {
        return managerController;
    }

    private ManagerController() {
    }

    /****************************************************methods********************************************************/

    public List<Account> viewAllAccounts() {
        return Account.getList();
    }

    public List<DiscountCode> viewDiscountCodes() {
        return DiscountCode.getList();
    }

    public Account viewAccount(String username) throws AccountDoesNotExistException {
        return Account.getAccountByUserName(username);
    }

    public void deleteAccount(String username) throws Exception {
        Account account = Account.getAccountByUserName(username);
        Account.deleteAccount(account);
    }

    public void removeProduct(String strProductId) throws Exception {
        long productId = Long.parseLong(strProductId);
        Product product = Product.getProductById(productId);
        Product.removeProduct(product);
    }

    public void creatDiscountCode(String strStart, String strEnd, String strPercent, String strMaxAmount, String strFrequentUse) throws Exception {

        LocalDate start = LocalDate.parse(strStart, formatter);
        LocalDate end = LocalDate.parse(strEnd, formatter);

        double percent = Double.parseDouble(strPercent);
        double maxAmount = Double.parseDouble(strMaxAmount);
        int frequentUse = Integer.parseInt(strFrequentUse);

        if (start.isBefore(end) && end.isAfter(LocalDate.now())) {
            DiscountCode discountCode = new DiscountCode();
            DiscountCode.addDiscountCode(discountCode);
        } else
            throw new InvalidStartAndEndDateForDiscountCodeException("StartAndEnd date are Invalid.");
    }

    public DiscountCode viewDiscountCode(String DisscoutCodeId) throws DiscountCodeExpiredExcpetion {
        return DiscountCode.getDiscountCodeById(disscoutCodeId);
    }

    public void editDiscountCode(String strDiscountCodeId, String field,String newField) {
        ///check kon ke customer bashe
        Customer customer = (Customer) controllerUnit.getAccount();
        //Field field = DiscountCode.getField;
        //field.set(controllerUnit.getAccount(), newField);

    }

    public void removeDiscountCode(String StrDiscountCodeId) throws Exception {
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

    public Request detailsOfRequest(String strRequestId) throws RequesDoesNotExistException {
        Request request = Request.getRequestById(requestId);
        return request;
    }

    public void acceptRequest(String strRequestId) throws  RequesDoesNotExistException,Exception{
        Request request = Request.getRequestById(requestId);
        request.acceptRequest();
    }

    public void denyRequest(String strRequestId) throws Exception {
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
