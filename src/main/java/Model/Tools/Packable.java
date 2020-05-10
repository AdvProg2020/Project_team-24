package Model.Tools;

import Exceptions.AccountDoesNotExistException;
import Exceptions.DiscountCodeExpiredExcpetion;
import Exceptions.ProductDoesNotExistException;

public interface Packable {

    Data pack();

    void dpkg(Data data) throws ProductDoesNotExistException, AccountDoesNotExistException, DiscountCodeExpiredExcpetion;
}
