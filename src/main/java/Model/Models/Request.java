package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Tools.AddingNew;
import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Request implements Packable<Request> {

    private static List<Request> list;

    static {
        list = DataBase.loadList("Request").stream()
                .map(packable -> (Request) packable)
                .collect(Collectors.toList());
    }

    /*****************************************************fields*******************************************************/

    private long requestId;
    private long accountId;
    private String information;
    private String typeOfRequest;
    private ForPend forPend;

    /*****************************************************getters*******************************************************/

    public long getId() {
        return requestId;
    }

    public long getRequestId() {
        return requestId;
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
    //yac


    public static void setList(List<Request> list) {
        Request.list = list;
    }

    /*****************************************************setters*******************************************************/

    private void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public static void setList(List<Request> list) {
        Request.list = list;
    }

    /***************************************************otherMethods****************************************************/

    public void acceptRequest() throws CanNotRemoveFromDataBase, CanNotSaveToDataBaseException {
        forPend.setStateForPend("Accepted");
        if (forPend instanceof Product) {
            Product.addProduct((Product) forPend);
        } else if (forPend instanceof Auction) {
            Auction.addAuction((Auction) forPend);
        }
        list.remove(this);
        DataBase.remove(this);
    }

    public void declineRequest() throws CanNotRemoveFromDataBase {
        forPend.setStateForPend("Declined");
        list.remove(this);
        DataBase.remove(this);
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addRequest(Request request) throws  CanNotSaveToDataBaseException {
        request.setRequestId(AddingNew.getRegisteringId().apply(Request.getList()));
        list.add(request);
        DataBase.save(request, true);
    }

    public static void removeRequest(Request request) throws CanNotRemoveFromDataBase {
        list.remove(request);
        DataBase.remove(request);
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Request> pack() {
        return new Data<Request>()
                .addField(requestId)
                .addField(accountId)
                .addField(information)
                .addField(typeOfRequest)
                .addField(forPend)
                .setInstance(new Request());
    }

    @Override
    public Request dpkg(Data<Request> data) {
        this.requestId = (long) data.getFields().get(0);
        this.accountId = (long) data.getFields().get(1);
        this.information = (String) data.getFields().get(2);
        this.typeOfRequest = (String) data.getFields().get(3);
        this.forPend = (ForPend) data.getFields().get(4);
        return this;
    }

    /***************************************************otherMethods****************************************************/

    public static Request getRequestById(long id) throws RequestDoesNotExistException {
        return list.stream()
                .filter(request -> id == request.getId())
                .findFirst()
                .orElseThrow(() -> new RequestDoesNotExistException("Request with this id does not exist."));
    }

    /**************************************************constructors*****************************************************/

    public Request(long accountId, String information, String typeOfRequest, ForPend forPend) {
        this.accountId = accountId;
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
                ", accountId=" + accountId +
                ", information='" + information + '\'' +
                ", typeOfRequest='" + typeOfRequest + '\'' +
                ", forPend=" + forPend +
                '}';
    }
}
