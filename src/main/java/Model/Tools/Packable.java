package Model.Tools;

import Exceptions.AccountDoesNotExistException;
import Exceptions.DiscountCodeExpiredExcpetion;
import Exceptions.ProductDoesNotExistException;

public interface Packable <T extends Packable<T>>{

    Data<T> pack();

    T dpkg(Data<T> data) throws ProductDoesNotExistException, AccountDoesNotExistException, DiscountCodeExpiredExcpetion;

    long getId();
}
