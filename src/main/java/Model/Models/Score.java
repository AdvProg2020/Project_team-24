package Model.Models;

import Exceptions.AccountDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Score implements Packable<Score> {

    private static List<Score> list = new ArrayList<>();

    static {
        list = DataBase.loadList("Score").stream()
                .map(packable -> (Score) packable)
                .collect(Collectors.toList());
    }

    /******************************************************fields*******************************************************/

    public long scoreId;
    private Account user;
    private Product good;
    private int score;

    /*****************************************************getters*******************************************************/

    @Override
    public long getId() {
        return scoreId;
    }

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
        return Collections.unmodifiableList(list);
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addScore(Score score) throws Exception {
        list.add(score);
        DataBase.save(score,true);
    }

    public static void removeScore(Score score) throws Exception {
        list.remove(score);
        DataBase.remove(score);
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Score> pack() {
        return new Data<Score>()
                .addField(scoreId)
                .addField(user)
                .addField(good)
                .addField(score)
                .setInstance(new Score());
    }

    @Override
    public Score dpkg(Data<Score> data) throws ProductDoesNotExistException, AccountDoesNotExistException {
        this.scoreId = (long) data.getFields().get(0);
        this.user = (Account) data.getFields().get(1);
        this.good = (Product) data.getFields().get(2);
        this.score = (int) data.getFields().get(3);
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Score(long scoreId, Account user, int score, Product good) {
        this.scoreId = scoreId;
        this.user = user;
        this.score = score;
        this.good = good;
    }

    public Score() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        String goodName = "Exception";
        try {
            Field field = good.getProductInfo().getList().getFieldByName("name");
            goodName = ((SingleString) field).getString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Score{" +
                "scoreId=" + scoreId +
                ", user=" + user.getUserName() +
                ", good=" + goodName +
                ", score=" + score +
                '}';
    }
}
