package Model.Tools;

import Exceptions.*;

import java.util.List;

public interface Packable <T extends Packable<T>>{

    Data<T> pack();

    T dpkg(Data<T> data) throws ProductDoesNotExistException, AccountDoesNotExistException, DiscountCodeExpiredException, CategoryDoesNotExistException, CommentDoesNotExistException;

    long getId();

    static long getRegisteringId(List<? extends Packable<?>> list) {
        return list.stream().map(Packable::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;
    }
}
