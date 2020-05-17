package Controller.Controllers;

import Controller.Tools.RegisterAndLoginValidator;
import Controller.Tools.RegisterAndLoginValidator.RegisterValidation;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Structs.Discount;
import Model.Tools.AddingNew;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class ManagerController extends AccountController {

    /******************************************************fields*******************************************************/

    private static ManagerController managerController = new ManagerController();

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

    public List<Request> manageRequests() {
        return Request.getList();
    }

    public List<Category> manageCategories() {
        return Category.getList();
    }

    public List<Request> showAllRequests() {
        return Request.getList();
    }

    public List<Category> showAllCategories() {
        return Category.getList();
    }

    public Account viewAccount(String username) throws AccountDoesNotExistException {
        return Account.getAccountByUserName(username);
    }

    public void deleteAccount(String username) throws AccountDoesNotExistException, CanNotRemoveFromDataBase {
        Account account = Account.getAccountByUserName(username);
        Account.deleteAccount(account);
    }

    public void removeProduct(String strProductId) throws CanNotRemoveFromDataBase, ProductDoesNotExistException {
        long productId = Long.parseLong(strProductId);
        Product product = Product.getProductById(productId);
        Product.removeProduct(product);
    }

    public void creatDiscountCode(String strStart, String strEnd, String strPercent, String strMaxAmount, String strFrequentUse) throws CanNotSaveToDataBaseException, InvalidStartAndEndDateForDiscountCodeException {

        LocalDate start = LocalDate.parse(strStart, formatter);
        LocalDate end = LocalDate.parse(strEnd, formatter);

        double percent = Double.parseDouble(strPercent);
        double maxAmount = Double.parseDouble(strMaxAmount);
        int frequentUse = Integer.parseInt(strFrequentUse);

        if (start.isBefore(end) && end.isAfter(LocalDate.now())) {
            DiscountCode discountCode = new DiscountCode(
                    DiscountCode.createDiscountCode(),
                    start,
                    end,
                    new Discount(percent, maxAmount),
                    frequentUse
            );
            DiscountCode.addDiscountCode(discountCode);
        } else
            throw new InvalidStartAndEndDateForDiscountCodeException("StartAndEnd date are Invalid.");
    }

    public DiscountCode viewDiscountCode(String DiscountCodeIdString) throws DiscountCodeExpiredException, NumberFormatException {
        long discountCodeId = Long.parseLong(DiscountCodeIdString);
        return DiscountCode.getDiscountCodeById(discountCodeId);
    }

    public void editDiscountCode(String DiscountCodeIdString, String strField, String newField) throws NumberFormatException, DiscountCodeExpiredException, FieldDoesNotExistException {
        long discountCodeId = Long.parseLong(DiscountCodeIdString);
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(discountCodeId);
        discountCode.editField(strField,newField);
    }

    public void removeDiscountCode(String StrDiscountCodeId) throws DiscountCodeExpiredException, CanNotRemoveFromDataBase {
        long discountCodeId = Long.parseLong(StrDiscountCodeId);
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(discountCodeId);
        DiscountCode.removeFromDiscountCode(discountCode);
    }

    private Customer selectRandomBuyer() {
        Random randomAccount = new Random();
        List<Customer> customerList = Customer.getAllCustomers();
        int randomIndex = randomAccount.nextInt(customerList.size());
        return customerList.get(randomIndex);
    }

    private List<Customer> findSpecialBuyers() {
        return Customer.getSpecialCustomers();
    }

    public void setDiscountCodeToSpecials(long discountCodeId) throws DiscountCodeExpiredException {
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(discountCodeId);
        findSpecialBuyers().forEach(customer -> customer.addToDiscountCodeList(discountCode.getId()));
    }

    public void setDiscountCodeToRandoms(long discountCodeId) throws DiscountCodeExpiredException {
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(discountCodeId);
        selectRandomBuyer().addToDiscountCodeList(discountCode.getId());
    }

    public Request detailsOfRequest(String strRequestId) throws RequestDoesNotExistException {
        long requestId = Long.parseLong(strRequestId);
        return Request.getRequestById(requestId);
    }

    public void acceptRequest(String strRequestId) throws RequestDoesNotExistException, CanNotRemoveFromDataBase, CanNotSaveToDataBaseException {
        long requestId = Long.parseLong(strRequestId);
        ((Manager) controllerUnit.getAccount()).acceptRequest(Request.getRequestById(requestId));
    }

    public void denyRequest(String strRequestId) throws RequestDoesNotExistException, CanNotRemoveFromDataBase {
        long requestId = Long.parseLong(strRequestId);
        ((Manager) controllerUnit.getAccount()).declineRequest(Request.getRequestById(requestId));
    }

    public void editCategory(String categoryId, String fieldName, String newField) throws FieldDoesNotExistException, CategoryDoesNotExistException, NumberFormatException {
        long id = Long.parseLong(categoryId);
        Category category = Category.getCategoryById(id);
        category.editField(fieldName, newField);
    }

    public void removeCategory(String categoryId) throws CanNotRemoveFromDataBase, CategoryDoesNotExistException {
        long id = Long.parseLong(categoryId);
        Category category = Category.getCategoryById(id);
        Category.removeCategory(category);
    }

    public void addCategory(String categoryId) throws CanNotSaveToDataBaseException, CategoryDoesNotExistException {
        long id = Long.parseLong(categoryId);
        Category category = Category.getCategoryById(id);
        Category.addCategory(category);
    }

    public Manager createManagerProfileBaseAccount(String username) throws UserNameInvalidException, UserNameTooShortException {
        RegisterValidation registerValidation = RegisterAndLoginValidator.isUsername(username).get();

        switch (registerValidation) {
            case IS_NOT_A_VALID_USERNAME_CHAR:
                throw new UserNameInvalidException("UserNameInvalidException");
            case IS_NOT_A_VALID_USERNAME_TOO_SHORT:
                throw new UserNameTooShortException("UserNameTooShortException");
        }
        Manager manager = new Manager(username);
        Account.addToInRegisteringList(manager);
        return manager;
    }
}
