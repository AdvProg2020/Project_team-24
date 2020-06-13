package Graphics;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class RegisterController {

    public TextField username;
    public PasswordField password;
    public Label status;

    public void buttonHandler(@NotNull ActionEvent event){

        String button =((Button)event.getSource()).getText();

        switch (button) {
            case "بازگشت به صفحه ی اصلی":
                try {
                    Main.setRoot("MainMenu");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "مرحله بعدی":
                register(event);
                try {
                    Main.setRoot("ChooseTypeOFAccount");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "ورود":
                try {
                    Main.setRoot("Login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void register(ActionEvent actionEvent) {

        String usernameEntered = username.getText();
        String passwordEntered = password.getText();
        //controller + setting status.text
    }
}
