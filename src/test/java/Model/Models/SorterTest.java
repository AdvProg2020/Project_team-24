package Model.Models;

import Exceptions.CanNotSaveToDataBaseException;
import Model.Models.Accounts.Seller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {
    ///sorter ro ke new mikonim dar constructor noeash ro behesh midim ---> list.sort
    @BeforeEach
    void setAccountsToTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //accpunt
        Account account = new Seller("akbar");
        account.setId(1);
        //product1
        Product product1 = new Product("aftabe", null, null);
        Info info1 = new Info("time of upload",null, LocalDate.parse("24/3/1399",formatter));
        product1.setProductInfo(info1);
        try {
            product1.addSeller(1,50,2);
        } catch (CanNotSaveToDataBaseException e) {
            e.printStackTrace();
        }
        //product2
        Product product2 = new Product("laak", null, null);
        Info info2 = new Info("time of upload",null, LocalDate.parse("24/3/1395",formatter));
        product2.setProductInfo(info2);

        try {
            product2.addSeller(1,4,1);
        } catch (CanNotSaveToDataBaseException e) {
            e.printStackTrace();
        }
        //product3
        Product product3 = new Product("sandali", null, null);
        Info info3 = new Info("time of upload",null, LocalDate.parse("28/3/1399",formatter));
        product3.setProductInfo(info3);

        try {
            product3.addSeller(1,333,6);
        } catch (CanNotSaveToDataBaseException e) {
            e.printStackTrace();
        }
        List<Product> listOfProducts = Arrays.asList(product1, product2);
        Product.setList(listOfProducts);
        List<Long> productIds = null;
        for (Product product : listOfProducts) {
            productIds.add(product.getId());
        }
    }
    @Test
    void getDefaultComparator() {
        List<Product> list = Product.getList()
        Sorter sorter = new Sorter(Sorter.getPointComparator());
        sorter.sorted(list);





    }

    @Test
    void getVisitorComparator() {
    }

    @Test
    void getPointComparator() {
    }

    @Test
    void getTimeComparator() {
    }

    @Test
    void sorted() {
    }
}