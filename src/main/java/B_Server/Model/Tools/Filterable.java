package B_Server.Model.Tools;

import Exceptions.FieldDoesNotExistException;

public interface Filterable {

    String getField(String fieldName) throws FieldDoesNotExistException;
}
