package MessageFormates;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface MessageSupplier {

    static Map<RequestType, Function<List<String>, String>> setListOfSuppliers() {
        HashMap<RequestType, Function<List<String>, String>> requestTypeSupplierHashMap = new HashMap<>();

        requestTypeSupplierHashMap.put(RequestType.GetToken, list -> "Slm-Slm" + "::GetToken");

        requestTypeSupplierHashMap.put(RequestType.Logout, list -> list.get(0) + "::Logout");

        requestTypeSupplierHashMap.put(RequestType.Login, list -> list.get(0) + "::Login "
                + list.get(1) + " " + list.get(2));

        // Single Object
        requestTypeSupplierHashMap.put(RequestType.GetProductById, list -> list.get(0) + "::GetProductById "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.GetAccountById, list -> list.get(0) + "::GetAccountById "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.GetCodeById, list -> list.get(0) + "::GetCodeById "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.GetCateById, list -> list.get(0) + "::GetCateById "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.GetAuctionById, list -> list.get(0) + "::GetAuctionById "
                + list.get(1));

        // Get/Set image or movie
        requestTypeSupplierHashMap.put(RequestType.GetImageById, list -> list.get(0) + "::GetImageById "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.GetMovieById, list -> list.get(0) + "::GetMovieById "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.SetImageById, list -> list.get(0) + "::SetImageById "
                + list.get(1) + " " + list.get(2));

        requestTypeSupplierHashMap.put(RequestType.SetMovieById, list -> list.get(0) + "::SetMovieById "
                + list.get(1) + " " + list.get(2));

        requestTypeSupplierHashMap.put(RequestType.SetMedias, list -> list.get(0) + "::SetMedias "
                + list.get(1) + " " + list.get(2));

        // Get All
        requestTypeSupplierHashMap.put(RequestType.GetAllAccounts, list -> list.get(0) + "::GetAllAccounts");

        requestTypeSupplierHashMap.put(RequestType.GetAllAuctions, list -> list.get(0) + "::GetAllAuctions");

        requestTypeSupplierHashMap.put(RequestType.GetAllProductOfCart, list -> list.get(0) + "::GetAllProductOfCart "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.GetAllRequest, list -> list.get(0) + "::GetAllRequest");

        requestTypeSupplierHashMap.put(RequestType.GetAllCommentOfProduct, list -> list.get(0) + "::GetAllCommentOfProduct "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.GetLogsOfUserById, list -> list.get(0) + "::GetLogsOfUserById "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.GetAllProductOfAuction, list -> list.get(0) + "::GetAllProductOfAuction "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.GetAllProducts, list -> list.get(0) + "::GetAllProducts");

        requestTypeSupplierHashMap.put(RequestType.GetAllProductsPrime, list -> list.get(0) + "::GetAllProductsPrime");

        requestTypeSupplierHashMap.put(RequestType.GetAllDiscountCodes, list -> list.get(0) + "::GetAllDiscountCodes");

        requestTypeSupplierHashMap.put(RequestType.GetCodesOfUserById, list -> list.get(0) + "::GetCodesOfUserById "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.GetAllCate, list -> list.get(0) + "::GetAllCate");

        requestTypeSupplierHashMap.put(RequestType.GetAllProductOfCate, list -> list.get(0) + "::GetAllProductOfCate "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.GetAllMyProducts, list -> list.get(0) + "::GetAllMyProducts");

        requestTypeSupplierHashMap.put(RequestType.GetCartByUserId, list -> list.get(0) + "::GetCartByUserId "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.GetAllPopularProducts, list -> list.get(0) + "::GetAllPopularProducts");

        // Add Or Edit Or Delete
        requestTypeSupplierHashMap.put(RequestType.addNewAccount, list -> list.get(0) + "::addNewAccount");

        requestTypeSupplierHashMap.put(RequestType.addToCodesList, list -> list.get(0) + "::addToCodesList "
                + list.get(1) + " " + list.get(2));

        requestTypeSupplierHashMap.put(RequestType.addNewAuction, list -> list.get(0) + "::addNewAuction");

        requestTypeSupplierHashMap.put(RequestType.addNewCate, list -> list.get(0) + "::addNewCate");

        requestTypeSupplierHashMap.put(RequestType.addNewDiscountCode, list -> list.get(0) + "::addNewDiscountCode");

        requestTypeSupplierHashMap.put(RequestType.addNewProduct, list -> list.get(0) + "::addNewProduct");

        requestTypeSupplierHashMap.put(RequestType.EditAccount, list -> list.get(0) + "::EditAccount");

        requestTypeSupplierHashMap.put(RequestType.EditAuction, list -> list.get(0) + "::EditAuction");

        requestTypeSupplierHashMap.put(RequestType.EditCate, list -> list.get(0) + "::EditCate");

        requestTypeSupplierHashMap.put(RequestType.EditDiscountCode, list -> list.get(0) + "::EditDiscountCode");

        requestTypeSupplierHashMap.put(RequestType.EditProduct, list -> list.get(0) + "::EditProduct");

        requestTypeSupplierHashMap.put(RequestType.DeleteAccountById, list -> list.get(0) + "::DeleteAccountById "
                + list.get(1));

        // Other
        requestTypeSupplierHashMap.put(RequestType.addNewFilter, list -> list.get(0) + "::addNewFilter "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.Sort, list -> list.get(0) + "::Sort "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.acceptRequest, list -> list.get(0) + "::acceptRequest "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.declineRequest, list -> list.get(0) + "::declineRequest "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.increaseProduct, list -> list.get(0) + "::increaseProduct "
                + list.get(1) + " " + list.get(2));

        requestTypeSupplierHashMap.put(RequestType.decreaseProduct, list -> list.get(0) + "::decreaseProduct "
                + list.get(1) + " " + list.get(2));

        requestTypeSupplierHashMap.put(RequestType.CheckDiscountCodes, list -> list.get(0) + "::CheckCodesById");

        requestTypeSupplierHashMap.put(RequestType.addNewSellerOfPro, list -> list.get(0) + "::addNewSellerOfPro " +
                list.get(1) + " " + list.get(2) + " " + list.get(3));

        requestTypeSupplierHashMap.put(RequestType.saveInfoOfProduct, list -> list.get(0) + "::saveInfoOfProduct " +
                list.get(1) + " " + list.get(2) + " " + list.get(3) + " " + list.get(4));

        // Set Currents
        requestTypeSupplierHashMap.put(RequestType.SetCurrentCate, list -> list.get(0) + "::SetCurrentCate "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.SetCurrentCode, list -> list.get(0) + "::SetCurrentCode "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.SetCurrentProduct, list -> list.get(0) + "::SetCurrentProduct "
                + list.get(1));

        return requestTypeSupplierHashMap;
    }

    Map<RequestType, Function<List<String>, String>> messageSuppliers = Collections.unmodifiableMap(setListOfSuppliers());

    default String generateMessage(RequestType type, List<String> list) {
        return messageSuppliers.get(type).apply(list);
    }

    enum RequestType {
        GetToken,
        Login,
        Logout,
        GetProductById,
        GetAccountById,
        GetCateById,
        GetCartByUserId,
        GetAuctionById,
        GetImageById,
        GetMovieById,
        GetCodeById,
        SetImageById,
        SetMedias,
        SetMovieById,
        GetAllProducts,
        GetAllProductsPrime,
        GetAllAccounts,
        GetAllAuctions,
        GetAllRequest,
        GetAllDiscountCodes,
        GetCodesOfUserById,
        GetLogsOfUserById,
        GetAllCate,
        GetAllProductOfCate,
        GetAllProductOfAuction,
        GetAllCommentOfProduct,
        GetAllProductOfCart,
        GetAllMyProducts,
        GetAllPopularProducts,
        addNewAccount,
        addNewAuction,
        addNewCate,
        addNewProduct,
        addNewDiscountCode,
        EditAccount,
        EditAuction,
        EditCate,
        EditProduct,
        EditDiscountCode,
        DeleteAccountById,
        addNewFilter,
        CheckDiscountCodes,
        addNewSellerOfPro,
        saveInfoOfProduct,
        addToCodesList,
        acceptRequest,
        declineRequest,
        SetCurrentCate,
        SetCurrentCode,
        SetCurrentProduct,
        Sort,
        increaseProduct,
        decreaseProduct,
    }
}
