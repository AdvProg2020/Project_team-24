package Model.Tools;

import Exceptions.ProductDoesNotExistException;

public interface Packable {

    Data pack();

    void dpkg(Data data) throws ProductDoesNotExistException;
}
