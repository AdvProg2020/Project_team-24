package Graphics;

import Graphics.Tools.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController implements SceneBuilder {

    public TextField username;
    public PasswordField password;
    public Label status;

    @Override
    public Scene sceneBuilder() {
        return null;
    }

//    public void buttonHandler(ActionEvent event) {
//        String botton = ((Button) event.getSource()).getText();
//        if (botton.equals("بازگشت به صفحه ی اصلی")) {
//            try {
//                Main.setRoot("MainMenu");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else if (botton.equals("به خانواده سه سوت بپیوندید")) {
//            try {
//                Main.setRoot("RegisterBase");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else if (botton.equals(" ورود ")) {
//            login(event);
//            status.setText("شما وارد سایت شدید");
//            System.out.println("you have logged in successfully");
//        }
//    }

    public void login(ActionEvent actionEvent) {

        String usernameEntered = username.getText();
        String passwordEntered = password.getText();
        //controller + setting status.text
    }
}
