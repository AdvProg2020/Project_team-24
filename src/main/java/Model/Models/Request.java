package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Tools.AddingNew;
import Model.Models.Data.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class Request implements Packable<Request> {

    /*****************************************************fields*******************************************************/

    private static List<Request> list;

    private long requestId;
    private long accountId;
    private String information;
    private String typeOfRequest;
    private ForPend forPend;

    /*****************************************************getters*******************************************************/

    public long getId() {
        return requestId;
    }

    public long getAccountId() {
        return accountId;
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

    @NotNull
    @Contract(pure = true)
    public static List<Request> getList() {
        return Collections.unmodifiableList(list);
    }

    /*****************************************************setters*******************************************************/

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public static void setList(List<Request> list) {
        Request.list = list;
    }

    /***************************************************otherMethods****************************************************/

    public void acceptRequest() {
        forPend.setStateForPend("Accepted");

        switch (typeOfRequest) {
            case "new":
                accept_new();
                break;
            case "remove":
                accept_remove();
                break;
            case "edit":
                accept_edit();
        }

        list.remove(this);
        DataBase.remove(this);
    }

    private void accept_new() {
        if (forPend instanceof Product) {
            Auction auction = ((Product) forPend).getAuction();
            Category category = ((Product) forPend).getCategory();
            if (auction != null) {
                auction.addProductToAuction(((Product) forPend).getId());
            }
            if (category != null) {
                category.addToProductList(((Product) forPend).getId());
            }
            Product.addProduct((Product) forPend);
        } else if (forPend instanceof Auction) {
            Auction.addAuction((Auction) forPend);
        }
    }

    private void accept_edit() {
        accept_remove();
        accept_new();
    }

    private void accept_remove() {
        if (forPend instanceof Product) {
            Product.removeProduct((Product) forPend);
            Auction auction = ((Product) forPend).getAuction();
            Category category = ((Product) forPend).getCategory();
            if (auction != null) {
                auction.removeProductFromAuction(((Product) forPend).getId());
            }
            if (category != null) {
                category.removeFromProductList(((Product) forPend).getId());
            }
        } else if (forPend instanceof Auction) {
            Auction.removeAuction((Auction) forPend);
        }
    }

    public void declineRequest() {
        forPend.setStateForPend("Declined");
        list.remove(this);
        DataBase.remove(this);
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addRequest(@NotNull Request request) {
        request.setRequestId(AddingNew.getRegisteringId().apply(Request.getList()));
        list.add(request);
        DataBase.save(request, true);
    }

    public static void removeRequest(Request request) {
        list.removeIf(req -> request.getId() == req.getId());
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
    public Request dpkg(@NotNull Data<Request> data) {
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
                .orElseThrow(() -> new RequestDoesNotExistException(
                        "Request with the id:" + id + " does not exist in list of all request."
                ));
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
