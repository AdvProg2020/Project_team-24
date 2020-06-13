package Graphics.java;

import javafx.scene.control.Button;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ManagerAccountController {
    public void buttonHandler(ActionEvent event){
        String text = ((Button)event.getSource()).getText();
        if(text.equals("بازگشت به صفحه ی اصلی")){
            try {
                Main.setRoot("MainMenu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(text.equals("لیست کد های تخفیف")){
            try {
                Main.setRoot("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(text.equals("ویرایش کد تخفیف")){
            try {
                Main.setRoot("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(text.equals("ایجاد کد جدید")){
            try {
                Main.setRoot("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(text.equals("ایجاد مدیر جدید")){
            try {
                Main.setRoot("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(text.equals("لیست کاربران")){
            try {
                Main.setRoot("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(text.equals("حذف کاربر")){
            try {
                Main.setRoot("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(text.equals("لیست دسته ها")){
            try {
                Main.setRoot("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(text.equals("ویرایش دسته")){
            try {
                Main.setRoot("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(text.equals("افزودن دسته")){
            try {
                Main.setRoot("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
