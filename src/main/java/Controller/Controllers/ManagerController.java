package Controller.Controllers;

import Controller.Tools.RegisterAndLoginValidator;
import Controller.Tools.RegisterAndLoginValidator.RegisterValidation;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Field.Field;
import Model.Models.Structs.Discount;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    private Customer selectRandomBuyer() {
        Random randomAccount = new Random();
        List<Customer> customerList = Customer.getAllCustomers();
        int randomIndex = randomAccount.nextInt(customerList.size());
        return customerList.get(randomIndex);
    }

    private List<Customer> findSpecialBuyers() {
        return Customer.getSpecialCustomers();
    }

    public List<Account> viewAllAccounts() {
        return Account.getList();
    }

    public List<DiscountCode> viewDiscountCodes() {
        return DiscountCode.getList();
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

    public void deleteAccount(String username) throws AccountDoesNotExistException {
        Account account = Account.getAccountByUserName(username);
        if (account instanceof Customer) {
            for (Long aLong : ((Customer) account).getDiscountCodeList()) {
                DiscountCode discountCode;
                try {
                    discountCode = DiscountCode.getDiscountCodeById(aLong);
                    discountCode.checkExpiredDiscountCode();
                    discountCode.removeAccount(account.getId());
                } catch (DiscountCodeExpiredException e) {
                    // do nothing.ok?
                }
            }
        }
        Account.deleteAccount(account);
    }

    public void removeProduct(String strProductId) throws ProductDoesNotExistException {
        long productId = Long.parseLong(strProductId);
        Product product = Product.getProductById(productId);
        Product.removeProduct(product);
    }

    public void creatDiscountCode(String strStart, String strEnd, String strPercent, String strMaxAmount, String strFrequentUse) throws InvalidStartAndEndDateForDiscountCodeException {

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
            throw new InvalidStartAndEndDateForDiscountCodeException("Start and end date are Invalid.");
    }

    public DiscountCode viewDiscountCode(String DiscountCodeIdString) throws DiscountCodeExpiredException, NumberFormatException {
        long discountCodeId = Long.parseLong(DiscountCodeIdString);
        return DiscountCode.getDiscountCodeById(discountCodeId);
    }

    public void editDiscountCode(String DiscountCodeIdString, String strField, String newField) throws NumberFormatException, DiscountCodeExpiredException, FieldDoesNotExistException {
        long discountCodeId = Long.parseLong(DiscountCodeIdString);
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(discountCodeId);
        discountCode.editField(strField, newField);
    }

    public void removeDiscountCode(String StrDiscountCodeId) throws DiscountCodeExpiredException {
        long discountCodeId = Long.parseLong(StrDiscountCodeId);
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(discountCodeId);
        DiscountCode.removeFromDiscountCode(discountCode);
    }

    public void setDiscountCodeToSpecials(String discountCodeIdString) throws DiscountCodeExpiredException, NumberFormatException, AccountDoesNotExistException {
        long discountCodeId = Long.parseLong(discountCodeIdString);
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(discountCodeId);
        discountCode.checkExpiredDiscountCode();
        findSpecialBuyers().forEach(customer -> customer.addToDiscountCodeList(discountCode.getId()));
    }

    public void setDiscountCodeToRandoms(String discountCodeIdString) throws DiscountCodeExpiredException, NumberFormatException, AccountDoesNotExistException {
        long discountCodeId = Long.parseLong(discountCodeIdString);
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(discountCodeId);
        discountCode.checkExpiredDiscountCode();
        selectRandomBuyer().addToDiscountCodeList(discountCode.getId());
    }

    public Request detailsOfRequest(String strRequestId) throws RequestDoesNotExistException {
        long requestId = Long.parseLong(strRequestId);
        return Request.getRequestById(requestId);
    }

    public void acceptRequest(String strRequestId) throws RequestDoesNotExistException, AccountDoesNotExistException {
        long requestId = Long.parseLong(strRequestId);
        ((Manager) controllerUnit.getAccount()).acceptRequest(Request.getRequestById(requestId));
    }

    public void denyRequest(String strRequestId) throws RequestDoesNotExistException, AccountDoesNotExistException {
        long requestId = Long.parseLong(strRequestId);
        ((Manager) controllerUnit.getAccount()).declineRequest(Request.getRequestById(requestId));
    }

    public void editCategory(String categoryId, String fieldName, String newField) throws FieldDoesNotExistException, CategoryDoesNotExistException, NumberFormatException {
        long id = Long.parseLong(categoryId);
        Category category = Category.getCategoryById(id);
        category.editField(fieldName, newField);
    }

    public void removeCategory(String categoryId) throws CategoryDoesNotExistException {
        long id = Long.parseLong(categoryId);
        Category category = Category.getCategoryById(id);
        Category.removeCategory(category);
    }

    public void createEmptyCategory(String categoryName, @NotNull List<String> fieldNames, @NotNull List<String> strCategoriesIds) throws CategoryDoesNotExistException, NumberFormatException {

        List<Long> subCategory = strCategoriesIds.stream().map(Long::parseLong).collect(Collectors.toList());

        for (long aLong : subCategory) {
            Category.checkExistOfCategoryById(aLong);
        }

        List<Field> fields = fieldNames.stream().map(Field::new).collect(Collectors.toList());

        FieldList fieldList = new FieldList(fields);

        Category category = new Category(categoryName, fieldList, subCategory);

        Category.addCategory(category);
    }

    public Manager createManagerProfileBaseAccount(String username) throws UserNameInvalidException, UserNameTooShortException, ThisUserNameAlreadyExistsException {
        RegisterValidation registerValidation = RegisterAndLoginValidator.isUsername(username).get();

        switch (registerValidation) {
            case IS_NOT_A_VALID_USERNAME_CHAR:
                throw new UserNameInvalidException("UserNameInvalidException");
            case IS_NOT_A_VALID_USERNAME_TOO_SHORT:
                throw new UserNameTooShortException("UserNameTooShortException");
        }

        if (Account.isThereAnyAccountWithThisUsername(username) || Account.isThereAnyInRegisteringWithThisUsername(username)) {
            throw new ThisUserNameAlreadyExistsException("The username: " + username + " already exist.");
        }

        Manager manager = new Manager(username);
        Account.addToInRegisteringList(manager);
        return manager;
    }
}
