package Model.Models;

import Exceptions.ScoreDoesNotExistException;
import Model.Models.Accounts.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    @BeforeEach
    void setAccountsToTest() {
        //account
        Account account = new Customer("yasamingol");
        account.setId(1);
        //product
        Product product = new Product("atr",null,null, null);
        Product product2 = new Product("arduinouno",null,null, null);
        product.setProductId(1);
        product2.setProductId(2);
        List<Product> productlist = Arrays.asList(product,product2);
        Product.setList(productlist);
        //score
        Score score = new Score(1,1,50);
        score.setScoreId(1);
        List<Score> scoreList = Arrays.asList(score);
        Score.setList(scoreList);
    }
    @Test
    void addScore() {
        Account account = Account.getList().get(0);
        Product product = Product.getList().get(1);
        Score newscore = new Score(account.getId(),product.getId(),3);
        assertDoesNotThrow(() -> Score.addScore(newscore));
        assertTrue(Score.getList().contains(newscore));
    }

    @Test
    void removeScore() {
        Score score = Score.getList().get(0);
        assertDoesNotThrow(() -> Score.removeScore(score));
        assertFalse(Score.getList().contains(score));
    }


    @Test
    void getScoreById1() {
        Score scoreexpected = Score.getList().get(0);
        Score scoreactual = assertDoesNotThrow(() -> Score.getScoreById(1));
        assertEquals(scoreexpected,scoreactual);
    }
    @Test
    void getScoreById2() {
       assertThrows(ScoreDoesNotExistException.class,() -> Score.getScoreById(1000000000));
    }
    @Test
    void pack() {
    }

    @Test
    void dpkg() {
    }
}