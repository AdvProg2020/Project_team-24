package MessageInterfaces;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface MessageSupplier {

    static Map<RequestType, Function<List<String>,String>> setListOfSuppliers() {
        HashMap<RequestType, Function<List<String>,String>> requestTypeSupplierHashMap = new HashMap<>();

        requestTypeSupplierHashMap.put(RequestType.GetToken, list -> "Slm-Slm" + "::GetToken");
        // Single Object
        requestTypeSupplierHashMap.put(RequestType.GetProductById, list -> list.get(0) + "::GetProductById "
                + list.get(1));
        requestTypeSupplierHashMap.put(RequestType.GetAccountById, list -> list.get(0) + "::GetAccountById "
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
                + list.get(1) + list.get(2));
        requestTypeSupplierHashMap.put(RequestType.SetMovieById, list -> list.get(0) + "::SetMovieById "
                + list.get(1) + list.get(2));

        // Get All
        requestTypeSupplierHashMap.put(RequestType.GetAllAccounts, list -> list.get(0) + "::GetAllAccounts");

        requestTypeSupplierHashMap.put(RequestType.GetAllAuctions, list -> list.get(0) + "::GetAllAuctions");

        requestTypeSupplierHashMap.put(RequestType.GetAllProducts, list -> list.get(0) + "::GetAllProducts");

        requestTypeSupplierHashMap.put(RequestType.GetAllDiscountCodes, list -> list.get(0) + "::GetAllDiscountCodes");

        requestTypeSupplierHashMap.put(RequestType.GetAllCate, list -> list.get(0) + "::GetAllCate");

        requestTypeSupplierHashMap.put(RequestType.GetAllProductOfCate, list -> list.get(0) + "::GetAllProductOfCate "
                + list.get(1));

        requestTypeSupplierHashMap.put(RequestType.GetAllMyProducts, list -> list.get(0) + "::GetAllMyProducts");

        requestTypeSupplierHashMap.put(RequestType.GetAllPopularProducts, list -> list.get(0) + "::GetAllPopularProducts");

        // Add Or Edit
        requestTypeSupplierHashMap.put(RequestType.GetAllPopularProducts, list -> list.get(0) + "::GetAllPopularProducts");

        return requestTypeSupplierHashMap;
    }

    Map<RequestType, Function<List<String>,String>> messageSuppliers = Collections.unmodifiableMap(setListOfSuppliers());

    default String generateMessage(RequestType type, List<String> list) {
        return messageSuppliers.get(type).apply(list);
    }

    enum RequestType {
//        SetNewFilter,
//        Logout,
//        GetAllCategories,
//        GetAccountImage,
//        GetMiniAccount,
//        CheckMyDiscountCodes,
//        GetMyDiscountCodes,
//        EditFieldOfAccount,
//        DeleteMyAccount,
//        GetMyLogHistory,
//        SetImageOfAccount,
//        GetAllOfMyProducts,
//        EditFieldOfAuction,
//        AddNewAction,
//        EditFieldOfCate,
//        AddNewCate,
//        AddNewDiscountCode,
//        EditFieldOfDiscountCode,
        GetToken,
        GetProductById,
        GetAccountById,
        GetCateById,
        GetAuctionById,
        GetImageById,
        GetMovieById,
        SetImageById,
        SetMovieById,
        GetAllProducts,
        GetAllAccounts,
        GetAllAuctions,
        GetAllDiscountCodes,
        GetAllCate,
        GetAllProductOfCate,
        GetAllMyProducts,
        GetAllPopularProducts,
        addNewAccount,
        EditAccount,
    }
}
