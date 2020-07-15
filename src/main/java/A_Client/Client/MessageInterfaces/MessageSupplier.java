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
        requestTypeSupplierHashMap.put(RequestType.GetAllPopularProducts, list -> list.get(0) + "::GetAllPopularProducts");
        requestTypeSupplierHashMap.put(RequestType.GetAllProductsOfCategory, list -> list.get(0) + "::GetAllProductsOfCategory " + list.get(1));
        requestTypeSupplierHashMap.put(RequestType.SetNewFilter, list -> list.get(0) + "::SetNewFilter " + list.get(1));
        requestTypeSupplierHashMap.put(RequestType.Logout, list -> list.get(0) + "::Logout");
        requestTypeSupplierHashMap.put(RequestType.GetAllCategories, list -> list.get(0) + "::GetAllCategories");
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
    }
}
