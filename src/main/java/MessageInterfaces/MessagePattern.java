package MessageInterfaces;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface MessagePattern {

    Pattern splitTypeRequest = Pattern.compile("(.+)::(.+)");

    static Map<RequestType, Pattern> setListOfPatterns() {
        HashMap<RequestType, Pattern> requestTypeSupplierHashMap = new HashMap<>();
        requestTypeSupplierHashMap.put(RequestType.GetToken, Pattern.compile("^(.+)$"));
        requestTypeSupplierHashMap.put(RequestType.GetAllAuctions, Pattern.compile("^(.+)$"));
        requestTypeSupplierHashMap.put(RequestType.IDontKnow3, Pattern.compile(""));
        requestTypeSupplierHashMap.put(RequestType.IDontKnow4, Pattern.compile(""));
        return requestTypeSupplierHashMap;
    }

    Map<RequestType, Pattern> messageMatchers = Collections.unmodifiableMap(setListOfPatterns());

    default List<String> readMessage(String message) {
        Matcher matcher = splitTypeRequest.matcher(message);
        RequestType requestType = RequestType.valueOf(matcher.group(1));
        return readMessage(requestType, matcher.group(2));
    }

    default List<String> readMessage(RequestType type, String message) {
        Matcher matcher = messageMatchers.get(type).matcher(message);
        return IntStream.range(1,matcher.groupCount() + 1).mapToObj(matcher::group).collect(Collectors.toList());
    }

    enum RequestType {
        GetToken,
        GetAllAuctions,
        IDontKnow3,
        IDontKnow4
    }
}
