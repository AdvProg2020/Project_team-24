package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.List;

public class Request implements Packable {

    public enum TypeRequest {
        Product,Auction
    }

    public long requestId;
    private Account account;
    private TypeRequest typeRequest;
    private Object requestObject;

    public Account getAccount() {
        return account;
    }

    public TypeRequest getTypeRequest() {
        return typeRequest;
    }

    public Object getRequestObject() {
        return requestObject;
    }

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(requestId, account.accountId, typeRequest, requestObject);
    }

    public Request(long requestId, Account account, TypeRequest typeRequest, Object requestObject) {
        this.requestId = requestId;
        this.account = account;
        this.typeRequest = typeRequest;
        this.requestObject = requestObject;
    }
}
