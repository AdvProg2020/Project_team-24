package Graphics.Lists;

import Controller.Controllers.ManagerController;
import Graphics.MainMenu;
import Graphics.Tools.SceneBuilder;
import Model.Models.Account;
import Model.Models.DiscountCode;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DiscountCodeList implements SceneBuilder, Initializable {
    private static ManagerController managerController = ManagerController.getInstance();

    public TableView<DiscountCode> discountCodeList;
    public TableColumn<DiscountCode,Double> maxDiscount;
    public TableColumn<DiscountCode,String> discount;
    public TableColumn<DiscountCode, LocalDate> endDate;
    public TableColumn<DiscountCode,LocalDate> startDate;
    public TableColumn<DiscountCode,String> code;



    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src\\main\\resources\\Graphics\\DiscountCodeList\\DiscountCodeList.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    private void init() {
        List<DiscountCode> list = managerController.viewDiscountCodes();
        discountCodeList.setItems(FXCollections.observableList(list));
        code.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId() + ""));
        startDate.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getStart()));
        endDate.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getEnd()));
        discount.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDiscount().getPercent() + ""));
        maxDiscount.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDiscount().getAmount()));

    }

}
