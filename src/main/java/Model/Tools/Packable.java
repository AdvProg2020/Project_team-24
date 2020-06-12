package Model.Tools;

import Exceptions.*;
import Model.DataBase.Data;

public interface Packable <T extends Packable<T>>{

    Data<T> pack();

    T dpkg(Data<T> data) throws ProductDoesNotExistException, AccountDoesNotExistException, DiscountCodeExpiredException, CategoryDoesNotExistException, CommentDoesNotExistException, CartDoesNotExistException, LogHistoryDoesNotExistException, AuctionDoesNotExistException;

    long getId();
}
