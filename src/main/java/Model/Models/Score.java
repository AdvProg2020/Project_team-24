package Model.Models;

import Exceptions.AccountDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class Score implements Packable {

    private static List<Score> list;

    static {
        DataBase.loadList(Score.class);
    }

    /******************************************************fields*******************************************************/

    public long scoreId;
    private Account user;
    private Product good;
    private int score;

    /*****************************************************getters*******************************************************/

    public Account getUser() {
        return user;
    }

    public int getScore() {
        return score;
    }

    public Product getGood() {
        return good;
    }

    public static List<Score> getList() {
        return list;
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data pack() {
        return new Data(Score.class.getName())
                .addField(scoreId)
                .addField(user)
                .addField(good)
                .addField(score);
    }

    @Override
    public void dpkg(Data data) throws ProductDoesNotExistException, AccountDoesNotExistException {
        this.scoreId = (long) data.getFields().get(0);
        this.user = (Account) data.getFields().get(1);
        this.good = (Product) data.getFields().get(2);
        this.score = (int) data.getFields().get(3);
    }

    /**************************************************constructors*****************************************************/

    public Score(long scoreId, Account user, int score, Product good) {
        this.scoreId = scoreId;
        this.user = user;
        this.score = score;
        this.good = good;
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Score{" +
                "scoreId=" + scoreId +
                ", user=" + user.getUserName() +
                ", good=" + good.getProductInfo().getFieldByName("name") +
                ", score=" + score +
                '}';
    }
}
