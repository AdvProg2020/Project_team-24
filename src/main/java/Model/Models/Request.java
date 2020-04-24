package Model.Models;

import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.util.List;

public class Request implements Packable {

    private static List<Request> requestList;

    static {

    }

    public enum TypeRequest {
        Edit, New
    }

    public long requestId;
    private Account account;
    private TypeRequest typeRequest;
    private ForPend forPend;

    public Account getAccount() {
        return account;
    }

    public TypeRequest getTypeRequest() {
        return typeRequest;
    }

    public Object getForPend() {
        return forPend;
    }

    public static List<Request> getRequestList() {
        return requestList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Request(long requestId, Account account, TypeRequest typeRequest, ForPend forPend) {
        this.requestId = requestId;
        this.account = account;
        this.typeRequest = typeRequest;
        this.forPend = forPend;
    }
}
