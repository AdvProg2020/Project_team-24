package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Models.Accounts.Seller;
import Model.Tools.AddingNew;
import Model.Models.Data.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    public void acceptRequest() throws AccountDoesNotExistException {

        Seller seller = (Seller) Account.getAccountById(accountId);

        switch (typeOfRequest) {
            case "new":
                accept_new(true);
                break;
            case "remove":
                accept_remove();
                break;
            case "edit":
                accept_edit();
        }

        seller.removeFromPendList(forPend);

        list.remove(this);
        DataBase.remove(this);

        forPend.setStateForPend("Accepted");
    }

    private void accept_new(boolean New) throws AccountDoesNotExistException {

        Seller seller = (Seller) Account.getAccountById(accountId);

        if (forPend instanceof Product) {
            Auction auction = ((Product) forPend).getAuction();
            Category category = ((Product) forPend).getCategory();
            if (auction != null) {
                auction.addProductToAuction(((Product) forPend).getId());
            }
            if (category != null) {
                category.addToProductList(((Product) forPend).getId());
            }
            Product.addProduct((Product) forPend, New);
            seller.addToProductList(((Product) forPend).getId());
        } else if (forPend instanceof Auction) {
            Auction.addAuction((Auction) forPend , New);
            seller.addToAuctionList(((Auction) forPend).getId());
            ((Auction) forPend).getProductList().stream().map(aLong -> {
                try {
                    return Product.getProductById(aLong);
                } catch (ProductDoesNotExistException e) {
                    e.printStackTrace();
                }
                return null;
            }).filter(Objects::nonNull).filter(product -> product.getAuction() == null)
                    .forEach(product -> {
                        product.setAuction(((Auction) forPend));
                        DataBase.save((Auction) forPend);
                    });
        }
    }

    private void accept_edit() throws AccountDoesNotExistException {
        accept_remove();
        accept_new(false);
    }

    private void accept_remove() throws AccountDoesNotExistException {

        Seller seller = (Seller) Account.getAccountById(accountId);

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
            Product.removeProduct((Product) forPend);
            seller.removeFromProductList(((Product) forPend).getId());
        } else if (forPend instanceof Auction) {
            Auction.removeAuction((Auction) forPend);
            seller.removeFromAuctionList(((Auction) forPend).getId());
        }
    }

    public void declineRequest() throws AccountDoesNotExistException {

        Seller seller = (Seller) Account.getAccountById(accountId);

        seller.removeFromPendList(forPend);

        list.remove(this);
        DataBase.remove(this);

        forPend.setStateForPend("Declined");

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
