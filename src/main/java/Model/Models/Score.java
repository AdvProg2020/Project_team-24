package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.List;

public class Score implements Packable {

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

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(scoreId,score,user.accountId,good.productId);
    }

    public Score(long scoreId, Account user, int score, Product good) {
        this.scoreId = scoreId;
        this.user = user;
        this.score = score;
        this.good = good;
    }
}
