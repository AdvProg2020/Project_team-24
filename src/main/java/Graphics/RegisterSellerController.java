package Graphicss.java;

import Graphics.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterSellerController {
    public TextField companyname;
    public TextField email;
    public TextField phone;
    public Button submit;

    public void saveComapanyInfo() {
        String companyname2 = companyname.getText();
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
           saveComapanyInfo();
            System.out.println("اطلاعات ثبت شد");
        }
    }
}
