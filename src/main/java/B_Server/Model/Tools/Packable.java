package B_Server.Model.Tools;

import B_Server.Model.Models.Data.Data;
import Exceptions.*;

public interface Packable <T extends Packable<T>>{

    Data<T> pack();

    T dpkg(Data<T> data) throws ProductDoesNotExistException, AccountDoesNotExistException, DiscountCodeExpiredException, CategoryDoesNotExistException, CommentDoesNotExistException, CartDoesNotExistException, LogHistoryDoesNotExistException, AuctionDoesNotExistException;

    long getId();
}
