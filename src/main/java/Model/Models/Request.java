package Model.Models;

import Exceptions.AccountDoesNotExistException;
import Exceptions.RequesDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.util.Collections;
import java.util.List;

public class Request implements Packable {

    private static List<Request> list;

    static {
        DataBase.loadList(Request.class);
    }

    /*****************************************************fields*******************************************************/

    private long requestId;
    private Account account;
    private String information;
    private String typeOfRequest;
    private ForPend forPend;

    /*****************************************************getters*******************************************************/

    public long getRequestId() {
        return requestId;
    }

    public Account getAccount() {
        return account;
    }

    public String getTypeOfRequest() {
        return typeOfRequest;
    }

    public ForPend getForPend() {
        return forPend;
    }

    public String getInformation() {
        return information;
    }

    public static List<Request> getList() {
        return Collections.unmodifiableList(list);
    }

    /***************************************************otherMethods****************************************************/

    public void acceptRequest() {
        // add to all product or auction
        list.remove(this);
        DataBase.remove(this);
    }

    public void declineRequest() {
        // remove from all product or auction
        list.remove(this);
        DataBase.remove(this);
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addRequest(Request request) {
        list.add(request);
        DataBase.save(request);
    }

    public static void removeRequest(Request request) {
        list.remove(request);
        DataBase.remove(request);
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data pack() {
        return new Data(Request.class.getName())
                .addField(requestId)
                .addField(account.getId())
                .addField(information)
                .addField(typeOfRequest)
                .addField(forPend);
    }

    @Override
    public void dpkg(Data data) throws AccountDoesNotExistException {
        this.requestId = (long) data.getFields().get(0);
        this.account = Account.getAccountById((long) data.getFields().get(1));
        this.information = (String) data.getFields().get(2);
        this.typeOfRequest = (String) data.getFields().get(3);
        this.forPend = (ForPend) data.getFields().get(4);
    }

    /***************************************************otherMethods****************************************************/

    public static Request getRequestById(long id) throws RequesDoesNotExistException {
        return list.stream()
                .filter(request -> id == request.getRequestId())
                .findFirst()
                .orElseThrow(() -> new RequesDoesNotExistException("Request with this id does not exist."));
    }

    /**************************************************constructors*****************************************************/

    public Request(long requestId, Account account, String information, String typeOfRequest, ForPend forPend) {
        this.requestId = requestId;
        this.account = account;
        this.information = information;
        this.typeOfRequest = typeOfRequest;
        this.forPend = forPend;
    }

    public Request() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", account=" + account +
                ", information='" + information + '\'' +
                ", typeOfRequest='" + typeOfRequest + '\'' +
                ", forPend=" + forPend +
                '}';
    }
}
