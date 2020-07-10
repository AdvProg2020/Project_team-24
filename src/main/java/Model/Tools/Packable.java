package Model.Tools;

import Exceptions.*;
import Model.Models.Data.Data;

public interface Packable <T extends Packable<T>>{

    Data<T> pack();

    T dpkg(Data<T> data) throws ProductDoesNotExistException, AccountDoesNotExistException, DiscountCodeExpiredException, CategoryDoesNotExistException, CommentDoesNotExistException, CartDoesNotExistException, LogHistoryDoesNotExistException, AuctionDoesNotExistException;

    long getId();
}
