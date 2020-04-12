package Model.RuntimeData;

import Model.Models.Request;

import java.io.File;
import java.util.List;

public class RequestSource {

    private static File requestList_File = new File("src/main/resources/allRequests");

    private static List<Request> requestList;

    static {

    }

    public static List<Request> getRequestList() {
        return requestList;
    }
}
