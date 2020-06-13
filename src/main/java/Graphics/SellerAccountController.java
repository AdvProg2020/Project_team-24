package Graphicss.java;

import Graphics.Main;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class SellerAccountController {
    public void buttonHandler(ActionEvent event) {
        String text = ((Button) event.getSource()).getText();
        if (text.equals("بازگشت به صفحه ی اصلی")) {
            try {
                Main.setRoot("MainMenu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (text.equals("محصولات فروشی")) {
            try {
                Main.setRoot("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (text.equals("سابقه ی فروش")) {
            try {
                Main.setRoot("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (text.equals("ویرایش اطلاعات")) {
            try {
                Main.setRoot("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (text.equals("خروج از حساب کاربری")) {
            try {
                Main.setRoot("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
