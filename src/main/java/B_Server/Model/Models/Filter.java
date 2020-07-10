package B_Server.Model.Models;

import Exceptions.FieldDoesNotExistException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class Filter implements Predicate<Product> {

    private String fieldName;
    private String fieldValue;

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    @Override
    public boolean test(@NotNull Product product) {
        try {
            return fieldValue.equals(product.getField(fieldName));
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Filter(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
