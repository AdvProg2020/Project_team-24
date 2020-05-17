package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;
import Model.Tools.AddingNew;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Score implements Packable<Score> {

    private static List<Score> list;

    /******************************************************fields*******************************************************/

    private long scoreId;
    private long userId;
    private long goodId;
    private int scoreNum;

    /*****************************************************setters*******************************************************/

    private void setScoreId(long scoreId) {
        this.scoreId = scoreId;
    }

    public static void setList(List<Score> list) {
        Score.list = list;
    }

    /*****************************************************getters*******************************************************/

    @Override
    public long getId() {
        return scoreId;
    }

    public long getUserId() {
        return userId;
    }

    public int getScoreNum() {
        return scoreNum;
    }

    public long getGoodId() {
        return goodId;
    }

    public static List<Score> getList() {
        return Collections.unmodifiableList(list);
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addScore(Score score) throws CanNotSaveToDataBaseException {
        score.setScoreId(AddingNew.getRegisteringId().apply(Score.getList()));
        list.add(score);
        DataBase.save(score,true);
    }

    public static void removeScore(Score score) throws CanNotRemoveFromDataBase {
        list.remove(score);
        DataBase.remove(score);
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Score> pack() {
        return new Data<Score>()
                .addField(scoreId)
                .addField(userId)
                .addField(goodId)
                .addField(scoreNum)
                .setInstance(new Score());
    }

    @Override
    public Score dpkg(Data<Score> data) {
        this.scoreId = (long) data.getFields().get(0);
        this.userId = (long) data.getFields().get(1);
        this.goodId = (long) data.getFields().get(2);
        this.scoreNum = (int) data.getFields().get(3);
        return this;
    }
    /***************************************************otherMethod*****************************************************/

    public static Score getScoreById(long id) {
        return list.stream()
                .filter(score -> id == score.getId())
                .findFirst()
                .orElseThrow(); // need new Exception. maybe...
    }

    /**************************************************constructors*****************************************************/

    public Score(long userId, long goodId, int scoreNum) {
        this.userId = userId;
        this.goodId = goodId;
        this.scoreNum = scoreNum;
    }

    private Score() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Score{" +
                "scoreId=" + scoreId +
                ", userId=" + userId +
                ", goodId=" + goodId +
                ", scoreNum=" + scoreNum +
                '}';
    }
}
