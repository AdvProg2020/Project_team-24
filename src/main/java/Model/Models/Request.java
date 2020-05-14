package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Request implements Packable<Request> {

    private static List<Request> list = new ArrayList<>();

    static {
        list = DataBase.loadList("Request").stream()
                .map(packable -> (Request) packable)
                .collect(Collectors.toList());
    }

    /*****************************************************fields*******************************************************/

    private long requestId;
    private Account account;
    private String information;
    private String typeOfRequest;
    private ForPend forPend;

    /*****************************************************getters*******************************************************/

    public long getId() {
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

    public void acceptRequest() throws CanNotRemoveFromDataBase {
        // add to all product or auction
        list.remove(this);
        DataBase.remove(this);
    }

    public void declineRequest() throws  CanNotRemoveFromDataBase {
        // remove from all product or auction
        list.remove(this);
        DataBase.remove(this);
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addRequest(Request request) throws CanNotAddException, CanNotSaveToDataBaseException, IOException {
        list.add(request);
        DataBase.save(request,true);
    }

    public static void removeRequest(Request request) throws CanNotRemoveException, CanNotRemoveFromDataBase {
        list.remove(request);
        DataBase.remove(request);
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Request> pack() {
        return new Data<Request>()
                .addField(requestId)
                .addField(account.getId())
                .addField(information)
                .addField(typeOfRequest)
                .addField(forPend)
                .setInstance(new Request());
    }

    @Override
    public Request dpkg(Data<Request> data) throws AccountDoesNotExistException {
        this.requestId = (long) data.getFields().get(0);
        this.account = Account.getAccountById((long) data.getFields().get(1));
        this.information = (String) data.getFields().get(2);
        this.typeOfRequest = (String) data.getFields().get(3);
        this.forPend = (ForPend) data.getFields().get(4);
        return this;
    }

    /***************************************************otherMethods****************************************************/

    public static Request getRequestById(long id) throws RequesDoesNotExistException {
        return list.stream()
                .filter(request -> id == request.getId())
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

    private Request() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", account=" + account.getUserName() +
                ", information='" + information + '\'' +
                ", typeOfRequest='" + typeOfRequest + '\'' +
                ", forPend=" + forPend +
                '}';
    }
}
