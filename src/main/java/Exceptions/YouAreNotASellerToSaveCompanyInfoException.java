package Exceptions;

import jdk.jshell.spi.ExecutionControlProvider;

public class YouAreNotASellerToSaveCompanyInfoException extends Exception {
    public  YouAreNotASellerToSaveCompanyInfoException(String message){
        super(message);
    }
}
