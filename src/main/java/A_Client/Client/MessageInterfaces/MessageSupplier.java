package A_Client.Client.MessageInterfaces;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface MessageSupplier {

    static Map<RequestType, Function<List<String>,String>> setListOfSuppliers() {
        HashMap<RequestType, Function<List<String>,String>> requestTypeSupplierHashMap = new HashMap<>();
        requestTypeSupplierHashMap.put(RequestType.SetNewToken, list -> "SetNewToken::Please");
        requestTypeSupplierHashMap.put(RequestType.GetAllAuctions, list -> list.get(0) + "::GetAllAuctions");
        requestTypeSupplierHashMap.put(RequestType.GetAllProducts, list -> list.get(0) + "::GetAllProducts");
        requestTypeSupplierHashMap.put(RequestType.GetAllOfMyProducts, list -> list.get(0) + "::GetAllOfMyProducts");
        requestTypeSupplierHashMap.put(RequestType.GetAllPopularProducts, list -> list.get(0) + "::GetAllPopularProducts");
        requestTypeSupplierHashMap.put(RequestType.GetAllProductsOfCategory, list -> list.get(0) + "::GetAllProductsOfCategory "
                + list.get(1));
        requestTypeSupplierHashMap.put(RequestType.SetNewFilter, list -> list.get(0) + "::SetNewFilter "
                + list.get(1));
        requestTypeSupplierHashMap.put(RequestType.Logout, list -> list.get(0) + "::Logout");
        requestTypeSupplierHashMap.put(RequestType.GetAllCategories, list -> list.get(0) + "::GetAllCategories");
        requestTypeSupplierHashMap.put(RequestType.GetAccountImage, list -> list.get(0) + "::GetAccountImage");
        requestTypeSupplierHashMap.put(RequestType.GetMiniAccount, list -> list.get(0) + "::GetMiniAccount");
        requestTypeSupplierHashMap.put(RequestType.CheckMyDiscountCodes, list -> list.get(0) + "::CheckMyDiscountCode");
        requestTypeSupplierHashMap.put(RequestType.GetMyDiscountCodes, list -> list.get(0) + "::GetMyDiscountCodes");
        requestTypeSupplierHashMap.put(RequestType.DeleteMyAccount, list -> list.get(0) + "::DeleteMyAccount");
        requestTypeSupplierHashMap.put(RequestType.GetMyLogHistory, list -> list.get(0) + "::GetMyLogHistory");
        requestTypeSupplierHashMap.put(RequestType.EditFieldOfDiscountCode, list -> list.get(0) + "::EditFieldOfDiscountCode "
                + list.get(1) + " " + list.get(2) + " " + list.get(3));
        requestTypeSupplierHashMap.put(RequestType.SetImageOfAccount, list -> list.get(0) + "::SetImageOfAccount "
                + list.get(1));
        requestTypeSupplierHashMap.put(RequestType.EditFieldOfAccount, list -> list.get(0) + "::EditFieldOfAccount "
                + list.get(1) + " " + list.get(2));
        requestTypeSupplierHashMap.put(RequestType.EditFieldOfAuction, list -> list.get(0) + "::EditFieldOfAuction "
                + list.get(1) + " " + list.get(2) + " " + list.get(3) + " " + list.get(4));
        requestTypeSupplierHashMap.put(RequestType.AddNewAction, list -> list.get(0) + "::AddNewAction "
                + list.get(1) + " " + list.get(2) + " " + list.get(3) + " " + list.get(4) + " " + list.get(5) + " " + list.get(6));
        requestTypeSupplierHashMap.put(RequestType.GetCategoryById, list -> list.get(0) + "::GetCategoryById "
                + list.get(1));
        requestTypeSupplierHashMap.put(RequestType.EditFieldOfCate, list -> list.get(0) + "::EditFieldOfCate "
                        + list.get(1) + " " + list.get(2));
        requestTypeSupplierHashMap.put(RequestType.AddNewCate, list -> list.get(0) + "::EditFieldOfCate "
                + list.get(1) + " " + list.get(2) + " " + list.get(3));
        requestTypeSupplierHashMap.put(RequestType.GetProductById, list -> list.get(0) + "::GetProductById "
                + list.get(1));
        return requestTypeSupplierHashMap;
    }

    Map<RequestType, Function<List<String>,String>> messageSuppliers = Collections.unmodifiableMap(setListOfSuppliers());

    default String generateMessage(RequestType type, List<String> list) {
        return messageSuppliers.get(type).apply(list);
    }

    enum RequestType {
        SetNewToken,
        GetAllAuctions,
        GetAllProducts,
        GetAllPopularProducts,
        GetAllProductsOfCategory,
        SetNewFilter,
        Logout,
        GetAllCategories,
        GetAccountImage,
        GetMiniAccount,
        CheckMyDiscountCodes,
        GetMyDiscountCodes,
        EditFieldOfAccount,
        DeleteMyAccount,
        GetMyLogHistory,
        SetImageOfAccount,
        GetAllOfMyProducts,
        EditFieldOfAuction,
        AddNewAction,
        GetCategoryById,
        EditFieldOfCate,
        AddNewCate,
        AddNewDiscountCode,
        EditFieldOfDiscountCode,
        GetProductById,
    }
}
