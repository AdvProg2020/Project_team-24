package Graphics;

import javafx.scene.control.MenuItem;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChooseTypeOFAccountController {

    public void chooseType(ActionEvent event){
        String type = ((MenuItem)event.getSource()).getText();
        if(type.equals("فروشنده")){
            try {
                Main.setRoot("RegisterSeller");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(type.equals("خریدار")){
            try {
                Main.setRoot("RegusterCustomer");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
