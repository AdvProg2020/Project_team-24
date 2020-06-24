package Graphics;

import Controller.Controllers.BuyerController;
import Exceptions.ProductDoesNotExistException;
import Exceptions.ProductIsOutOfStockException;
import Exceptions.SellerDoesNotSellOfThisProduct;
import Model.Models.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class Cart implements Initializable {

    private BuyerController buyerController = BuyerController.getInstance();
    private Product selected;
    @FXML
    private Label totalPrice;
    @FXML
    private TableView<?> cart_Table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //?
    }

    public void increase() {
        try {
            buyerController.increase(selected.getId() + "", "0");
        } catch (ProductDoesNotExistException e) {
            productDoesNotExist();
        } catch (ProductIsOutOfStockException e) {
            productOutOfStack();
        } catch (SellerDoesNotSellOfThisProduct e) {
            e.printStackTrace();
        }
    }

    public void decrease() {
        try {
            buyerController.decrease(selected.getId() + "", "0");
        } catch (ProductDoesNotExistException e) {
            productDoesNotExist();
        }
    }

    public void back() {
        // ?
    }

    public void purchase() {
        MainMenu.change(new Payment().sceneBuilder());
    }

    private void productOutOfStack() {

    }

    private void productDoesNotExist() {

    }
}
