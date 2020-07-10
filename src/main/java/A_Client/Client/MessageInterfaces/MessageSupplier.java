package A_Client.Client.MessageInterfaces;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface MessageSupplier {

    static Map<RequestType, Function<List<String>,String>> setListOfSuppliers() {
        HashMap<RequestType, Function<List<String>,String>> requestTypeSupplierHashMap = new HashMap<>();
        requestTypeSupplierHashMap.put(RequestType.IDontKnow1, (list) -> "Patterns1");
        requestTypeSupplierHashMap.put(RequestType.IDontKnow2, (list) -> "Patterns2");
        requestTypeSupplierHashMap.put(RequestType.IDontKnow3, (list) -> "Patterns3");
        requestTypeSupplierHashMap.put(RequestType.IDontKnow4, (list) -> "Patterns4");
        return requestTypeSupplierHashMap;
    }

    Map<RequestType, Function<List<String>,String>> messageSuppliers = Collections.unmodifiableMap(setListOfSuppliers());

    static String generateMessage(RequestType type, List<String> list) {
        return messageSuppliers.get(type).apply(list);
    }

    enum RequestType {
        IDontKnow1,
        IDontKnow2,
        IDontKnow3,
        IDontKnow4
    }
}
