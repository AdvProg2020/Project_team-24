package A_Client.Client.MessageInterfaces;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface MessagePattern {

    static Map<RequestType, Pattern> setListOfPatterns() {
        HashMap<RequestType, Pattern> requestTypeSupplierHashMap = new HashMap<>();
        requestTypeSupplierHashMap.put(RequestType.IDontKnow1, Pattern.compile(""));
        requestTypeSupplierHashMap.put(RequestType.IDontKnow2, Pattern.compile(""));
        requestTypeSupplierHashMap.put(RequestType.IDontKnow3, Pattern.compile(""));
        requestTypeSupplierHashMap.put(RequestType.IDontKnow4, Pattern.compile(""));
        return requestTypeSupplierHashMap;
    }

    Map<RequestType, Pattern> messageMatchers = Collections.unmodifiableMap(setListOfPatterns());

    static List<String> readMessage(RequestType type, String message) {
        Matcher matcher = messageMatchers.get(type).matcher(message);
        return IntStream.range(1,matcher.groupCount() + 1).mapToObj(matcher::group).collect(Collectors.toList());
    }

    enum RequestType {
        IDontKnow1,
        IDontKnow2,
        IDontKnow3,
        IDontKnow4
    }
}
