package Model.Models;

import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FieldListTest {
    @BeforeEach
    void setAccountsToTest() {
        Field field1 = new SingleString("Title", "dar bareye aftabe");
        Field field2 =  new SingleString("Content", "mahsoole khaili khob va karbordi bood ,100 darsad pishnahad mishe");
        List<Field> fields = Arrays.asList(field1,field2);
        FieldList fieldList = new FieldList(fields);
        fieldList.setFieldList(fields);

    }

    @Test
    void removeField() {
        //chejoori mishe in list ro gereft?

    }

    @Test
    void addFiled() {
    }

    @Test
    void getFieldByName() {
        //?
    }

    @Test
    void isFieldWithThisName() {
        Field field = new Field("Title");
        //assertTrue(isFieldWithThisName("Title"));
    }
}