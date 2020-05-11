package Model.Tools;

import Exceptions.AccountDoesNotExistException;
import Exceptions.DiscountCodeExpiredExcpetion;
import Exceptions.ProductDoesNotExistException;

public interface Packable <T>{

    Data pack();

    T dpkg(Data data) throws ProductDoesNotExistException, AccountDoesNotExistException, DiscountCodeExpiredExcpetion;

    long getId();
}
