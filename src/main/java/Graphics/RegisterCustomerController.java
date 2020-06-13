package Graphicss.java;

import Graphics.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterCustomerController {
    public TextField firstname;
    public TextField lastname;
    public TextField email;
    public TextField phone;
    public Button sumbit;
    public Button back;

    public void savePersonalInfo() {
        String firstname2 = firstname.getText();
        String lastname2 = lastname.getText();
        String email2 = email.getText();
        String phone2 = phone.getText();
        //controller
    }

    public void buttonHandler(ActionEvent event) {
        String botton = ((Button) event.getSource()).getText();
        if (botton.equals("بازگشت به صفحه ی قبلی")) {
            try {
                Main.setRoot("ChooseTypeOFAccount");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (botton.equals("ثبت اطلاعات")) {
            savePersonalInfo();
            System.out.println("اطلاعات ثبت شد");
        }
    }
}
