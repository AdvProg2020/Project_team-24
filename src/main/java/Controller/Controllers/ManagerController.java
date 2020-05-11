package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

//list ha behtar nist beshe aray list?
//coment ha barrasid
public class ManagerController extends AccountController {
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;
    /****************************************************singleTone***************************************************/
    private static ManagerController  managerController;
    private ManagerController(ControllerUnit controllerUnit){
        this.controllerUnit = controllerUnit;
    }
    public static ManagerController getInstance(ControllerUnit controllerUnit){
        if(managerController == null){
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


    public void delete(String username) throws AccountDoesNotExistException {
        Account.deleteAccount(Account.getAccountByUserName(username));
    }

    public void removeProduct(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        ;
    }


    public void creatDiscountCode(Date start, Date end, double percent, double maxAmount, int frequentUse) {
        //check kardane inke end bade start bashe tarikhesh
        //har do az tarikjhe rooz bishtar bashan
        //+m creat disscoiunt code

    }

    public List<DiscountCode> viewDiscountCodes() {
        return DiscountCode.getList();
    }

    public DiscountCode viewDiscountCode(long disscoutCodeId) throws DiscountCodeExpiredExcpetion {
        return DiscountCode.getDiscountCodeById(disscoutCodeId);
    }

    public void editDiscountCode(long discountCodeId, String field) {
        //+m DiscountCode.getfieldByName(field)......
    }

    public void removeDiscountCode(long discountCodeId) throws DiscountCodeExpiredExcpetion {
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(discountCodeId);
        DiscountCode.removeFromDiscountCode(discountCode);
    }

    private ArrayList<Customer> findSpecialBuyers() {

        // yek halghe for darim ke beyne tamame customer ha miyad migarde onn hayi ke bish az i mill kharid kardan ro peyda mikone
        //+m neveshtane yek motheod baraye gereftane hameye customer va barresi 1mil bishter va tashkil list moshtari makhsoos
        return null;
    }

    private void setDiscountCodeToSpecials() {
        //inja miyaym be azaye list bala har koodoom code takhfif mididm/ add to Customer discountcodeList
    }

    public Customer selectRandomBuyers() {
        Random randomAccount = new Random();
        int listSize = Account.getList().size();
        int randomIndex = randomAccount.nextInt(listSize);
        return (Customer) Customer.getList().get(randomIndex);
        //+m estefade az hamoon method bala baraye gerefteane list customer
        ///yek list az tamame moshtari ha mikhaym
    }

    private void setDiscountCodeToRandoms(DiscountCode discountCode) {
        selectRandomBuyers().getDiscountCodeList().add(discountCode);

    }

    public List<Request> manageRequests() {
        return Request.getList();
    }

    public ArrayList<String> detailsOfRequest(long requestId) throws RequesDoesNotExistException {
        Request request = Request.getRequestById(requestId);
        ArrayList<String> detailsOfRequest = new ArrayList<String>();
        detailsOfRequest.add(request.getAccount().toString());
        detailsOfRequest.add(request.getForPend().toString());
        detailsOfRequest.add(request.getTypeOfRequest().toString());
        //!!detailsOfRequest.add(request.getRequestId());
        ///+m field tozihate request
        return detailsOfRequest;
    }

    public void requestAcceptedOrDeclined(long requestId, String acceptorDecline) throws InvalidStateOfRequest, RequesDoesNotExistException {
        //accept/decine ro do tike kon
        //+m yek state bayad baraye request bashe ke begim Declined ya na ke vaghti tabe ro call kardam state ro moshakhas kone
        if (acceptorDecline.equals("Accept")) {
            Request.getRequestById(requestId).acceptRequest();

        } else if (acceptorDecline.equals("Decline")) {
            Request.getRequestById(requestId).declineRequest();

        } else throw new InvalidStateOfRequest("InvalidStateOfRequest");

    }

    public List<Category> manageCategories() {
        return Category.getList();
    }

    public void editCategory(String categoryname, String field) {
        Category category1 = Category.getCategoryByName(categoryname);
        //kodoom field??
        //+m mitoonim mesle onn yeki class method tooye model dashte bashim barash
    }

    public void removeCategory(String categoryName) {
        Category category = Category.getCategoryByName(categoryName);
        Category.getList().remove(category);
    }

    public void addCategory(String categoryName) {
        Category category = Category.getCategoryByName(categoryName);
        Category.getList().add(category);
    }

    public void createManagerProfileBaseAccount(String username) {
        //inja bayad ooon boolean ro bezari

    }

    public List<Request> showAllRequests() {
        return Request.getList();
    }

    public List<Category> showAllCategories() {
        return Category.getList();
    }
}
