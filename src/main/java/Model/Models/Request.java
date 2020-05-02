package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.ForPend;

import java.util.List;

public class Request implements Packable {

    private static List<Request> requestList;

    static {
        DataBase.loadList(Request.class);
    }

    public enum TypeRequest {
        Edit, New
    }

    private long requestId;
    private Account account;
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

    public void acceptRequest() {
    }

    public void declineRequest() {

    }

    public static List<Request> getRequestList() {
        return requestList;
    }

    public static Request getRequestById(long id) {
        return requestList.stream().filter(request -> id == request.getRequestId()).findFirst().orElse(null);
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Request(long requestId, Account account, TypeRequest typeOfRequest, ForPend forPend) {
        this.requestId = requestId;
        this.account = account;
        this.typeOfRequest = typeOfRequest;
        this.forPend = forPend;
    }
    /////yac

}
