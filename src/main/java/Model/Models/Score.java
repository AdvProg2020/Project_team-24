package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Tools.AddingNew;
import Model.Tools.Data;
import Model.Tools.Packable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class Score implements Packable<Score> {

    /******************************************************fields*******************************************************/

    private static List<Score> list;

    private long scoreId;
    private long userId;
    private long goodId;
    private int scoreNum;

    /*****************************************************setters*******************************************************/

    public void setScoreId(long scoreId) {
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

//    public long getUserId() {
//        return userId;
//    }
//
//    public int getScoreNum() {
//        return scoreNum;
//    }
//
//    public long getGoodId() {
//        return goodId;
//    }

    @NotNull
    @Contract(pure = true)
    public static List<Score> getList() {
        return Collections.unmodifiableList(list);
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addScore(@NotNull Score score) {
        score.setScoreId(AddingNew.getRegisteringId().apply(Score.getList()));
        list.add(score);
        DataBase.save(score,true);
    }

    public static void removeScore(Score score) {
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
    public Score dpkg(@NotNull Data<Score> data) {
        this.scoreId = (long) data.getFields().get(0);
        this.userId = (long) data.getFields().get(1);
        this.goodId = (long) data.getFields().get(2);
        this.scoreNum = (int) data.getFields().get(3);
        return this;
    }

    /***************************************************otherMethod*****************************************************/

    public static Score getScoreById(long id) throws ScoreDoesNotExistException {
        return list.stream()
                .filter(score -> id == score.getId())
                .findFirst()
                .orElseThrow(() -> new ScoreDoesNotExistException(
                        "Score with the id:" + id + " doesn't exist in list of total scores")
                );
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
