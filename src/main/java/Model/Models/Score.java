package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class Score implements Packable {

    private static final String scoreSource
            = "src/main/resources/allScores";

    private static List<Score> scoreList;

    static {

    }

    public long scoreId;
    private Account user;
    private Product good;
    private int score;

    public Account getUser() {
        return user;
    }

    public int getScore() {
        return score;
    }

    public Product getGood() {
        return good;
    }

    public static List<Score> getScoreList() {
        return scoreList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Score(long scoreId, Account user, int score, Product good) {
        this.scoreId = scoreId;
        this.user = user;
        this.score = score;
        this.good = good;
    }
}
