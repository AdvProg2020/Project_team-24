package Model.Models;

import Model.Models.Field.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FieldListTest {
    @BeforeEach
    void setAccountsToTest() {
        Field field1 = new Field("Title", "dar bareye aftabe");
        Field field2 =  new Field("Content", "mahsoole khaili khob va karbordi bood ,100 darsad pishnahad mishe");
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