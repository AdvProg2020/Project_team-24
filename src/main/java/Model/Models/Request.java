package Model.Models;

import Exceptions.ProductDoesNotExistException;
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

    public enum TypeRequest {
        Edit, New, Remove
    }

    private long requestId;
    private Account account;
    private String information;
    private TypeRequest typeOfRequest;
    private ForPend forPend;

    public long getRequestId() {
        return requestId;
    }

    public Account getAccount() {
        return account;
    }

    public TypeRequest getTypeOfRequest() {
        return typeOfRequest;
    }

    public ForPend getForPend() {
        return forPend;
    }

    public String getInformation() {
        return information;
    }

    public void acceptRequest() {

    }

    public void declineRequest() {

    }

    public static List<Request> getList() {
        return Collections.unmodifiableList(list);
    }

    public static void addRequest(Request request) {
        list.add(request);
        DataBase.save(request);
    }

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
    public void dpkg(Data data) throws ProductDoesNotExistException {
        this.requestId = (long) data.getFields().get(0);
        this.account = Account.getAccountById((long) data.getFields().get(1));
        this.information = (String) data.getFields().get(2);
        this.typeOfRequest = (TypeRequest) data.getFields().get(3);
        this.forPend = (ForPend) data.getFields().get(4);
    }

    public static Request getRequestById(long id) {
        return list.stream()
                .filter(request -> id == request.getRequestId())
                .findFirst()
                .orElseThrow();
    }

    public Request(long requestId, Account account, String information, TypeRequest typeOfRequest, ForPend forPend) {
        this.requestId = requestId;
        this.account = account;
        this.information = information;
        this.typeOfRequest = typeOfRequest;
        this.forPend = forPend;
    }

    public Request() {
    }
}
