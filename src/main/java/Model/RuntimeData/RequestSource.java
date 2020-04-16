package Model.RuntimeData;

import Model.Models.Request;
import Model.Tools.FileHandler;

import java.io.File;
import java.util.List;

public class RequestSource implements PackClass, FileHandler {

    private static File requestList_File = new File("src/main/resources/allRequests");

    private static List<Request> requestList;

    static {

    }

    public static List<Request> getRequestList() {
        return requestList;
    }
}
