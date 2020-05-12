package Model.Tools;

import Exceptions.AccountDoesNotExistException;
import Exceptions.DiscountCodeExpiredException;
import Exceptions.ProductDoesNotExistException;

public interface Packable <T extends Packable<?>>{

    Data pack();

    T dpkg(Data data) throws ProductDoesNotExistException, AccountDoesNotExistException, DiscountCodeExpiredException;

    long getId();
}
