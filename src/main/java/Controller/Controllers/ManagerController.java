package Controller.Controllers;

import Controller.ControllerUnit;
import Controller.Tools.RegisterAndLoginValidator;
import Controller.Tools.RegisterAndLoginValidator.RegisterValidation;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Field.Fields.RangeString;
import Model.Tools.Packable;

import java.io.IOException;
import java.lang.IllegalAccessException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void deleteAccount(String username) throws AccountDoesNotExistException, CanNotRemoveFromDataBase, CanNotDeleteException {
        Account account = Account.getAccountByUserName(username);
        Account.deleteAccount(account);
    }

    public void removeProduct(String strProductId) throws CanNotRemoveException, CanNotRemoveFromDataBase, ProductDoesNotExistException {
        long productId = Long.parseLong(strProductId);
        Product product = Product.getProductById(productId);
        Product.removeProduct(product);
    }

    public void creatDiscountCode(String strStart, String strEnd, String strPercent, String strMaxAmount, String strFrequentUse) throws CanNotAddException, IOException, CanNotSaveToDataBaseException, InvalidStartAndEndDateForDiscountCodeException {

        LocalDate start = LocalDate.parse(strStart, formatter);
        LocalDate end = LocalDate.parse(strEnd, formatter);

        double percent = Double.parseDouble(strPercent);
        double maxAmount = Double.parseDouble(strMaxAmount);
        int frequentUse = Integer.parseInt(strFrequentUse);

        if (start.isBefore(end) && end.isAfter(LocalDate.now())) {
            DiscountCode discountCode = new DiscountCode(
                    Packable.getRegisteringId(DiscountCode.getList()),
                    DiscountCode.createDiscountCode(),
                    start,
                    end,
                    new Discount(percent, maxAmount),
                    frequentUse,
                    null,
                    null
            );
            DiscountCode.addDiscountCode(discountCode);
        } else
            throw new InvalidStartAndEndDateForDiscountCodeException("StartAndEnd date are Invalid.");
    }

    public DiscountCode viewDiscountCode(String DiscountCodeIdString) throws DiscountCodeExpiredException, NumberFormatException {
        long discountCodeId = Long.parseLong(DiscountCodeIdString);
        return DiscountCode.getDiscountCodeById(discountCodeId);
    }

    public void editDiscountCode(String DiscountCodeIdString, String strField, String newField) throws NumberFormatException, DiscountCodeExpiredException, NoSuchFieldException, IllegalAccessException {
        long discountCodeId = Long.parseLong(DiscountCodeIdString);
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(discountCodeId);
        Field field = DiscountCode.getFieldByName(strField);
        switch (field.getType().getSimpleName()) {
            case "LocalDate":
                field.set(discountCode, LocalDate.parse(newField, formatter));
                break;
            case "Integer":
                field.set(discountCode, Integer.parseInt(newField));
                break;
            case "Double":
                field.set(discountCode, Double.parseDouble(newField));
        }
    }

    public void removeDiscountCode(String StrDiscountCodeId) throws DiscountCodeExpiredException, CanNotRemoveException, CanNotRemoveFromDataBase {
        long discountCodeId = Long.parseLong(StrDiscountCodeId);
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(discountCodeId);
        DiscountCode.removeFromDiscountCode(discountCode);
    }

    private List<Customer> findSpecialBuyers() {
        return Customer.getSpecialCustomers();
    }

    private void setDiscountCodeToSpecials() {
        DiscountCode discountCode = new DiscountCode(); // auto create discount code
        findSpecialBuyers().forEach(customer -> {
            customer.addToDiscountCodeList(discountCode);
        });
    }

    public Customer selectRandomBuyer() {
        Random randomAccount = new Random();
        int randomIndex = randomAccount.nextInt(Account.getList().size());
        return (Customer) Customer.getList().get(randomIndex);
    }

    private void setDiscountCodeToRandoms(DiscountCode discountCode) {
        selectRandomBuyer().addToDiscountCodeList(discountCode);
    }

    public Request detailsOfRequest(String strRequestId) throws RequesDoesNotExistException {
        long requestId = Long.parseLong(strRequestId);
        return Request.getRequestById(requestId);
    }

    public void acceptRequest(String strRequestId) throws RequesDoesNotExistException, CanNotRemoveFromDataBase {
        long requestId = Long.parseLong(strRequestId);
        Request.getRequestById(requestId).acceptRequest();
    }

    public void denyRequest(String strRequestId) throws RequesDoesNotExistException, CanNotRemoveFromDataBase {
        long requestId = Long.parseLong(strRequestId);
        Request.getRequestById(requestId).declineRequest();
    }

    public void editCategory(String categoryName, String fieldName, String newField) throws FieldDoesNotExistException, NoSuchFieldException, IllegalAccessException {
        Category category = Category.getCategoryByName(categoryName);
        if (fieldName.matches("^FieldList")) {
            Model.Models.Field.Field modelField = category.getCategoryField().getFieldByName(fieldName);
            switch (modelField.getClass().getSimpleName()) {
                case "Field":
                    Matcher matcher = Pattern.compile("FieldList (\\w+)").matcher(newField);
                    if (!matcher.find()) {
                        // throw exception.
                    }
                    modelField.setFieldName(matcher.group(1));
                    break;
                case "RangeString":
                    matcher = Pattern.compile("FieldList (\\w+) : (\\w+) to (\\w+)").matcher(newField);
                    if (!matcher.find()) {
                        // throw exception.
                    }
                    modelField.setFieldName(matcher.group(1));
                    ((RangeString) modelField).setLow(matcher.group(2));
                    ((RangeString) modelField).setHigh(matcher.group(3));
                    break;
                case "SingleString":
                    matcher = Pattern.compile("FieldList (\\w+) : (\\w+)").matcher(newField);
                    if (!matcher.find()) {
                        // throw exception.
                    }
                    modelField.setFieldName(matcher.group(1));
                    ((RangeString) modelField).setLow(matcher.group(2));
            }
        } else {
            Field field = category.getClassFieldByName(fieldName);
            field.set(category, newField);
        }
    }

    public void removeCategory(String categoryName) throws CanNotRemoveException, CanNotRemoveFromDataBase {
        Category category = Category.getCategoryByName(categoryName);
        Category.removeCategory(category);
    }

    public void addCategory(String categoryName) throws CanNotAddException, IOException, CanNotSaveToDataBaseException {
        Category category = Category.getCategoryByName(categoryName);
        Category.addCategory(category);
    }

    public void createManagerProfileBaseAccount(String username) throws UserNameInvalidException, UserNameTooShortException {
        RegisterValidation registerValidation = RegisterAndLoginValidator.isUsername(username).get();

        switch (registerValidation) {
            case IS_NOT_A_VALID_USERNAME_CHAR:
                throw new UserNameInvalidException("UserNameInvalidException");
            case IS_NOT_A_VALID_USERNAME_TOO_SHORT:
                throw new UserNameTooShortException("UserNameTooShortException");
        }

        Manager manager = new Manager(username);
        Account.addToInRegisteringList(manager);
    }
}
