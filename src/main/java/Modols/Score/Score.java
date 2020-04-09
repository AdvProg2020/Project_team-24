package Modols.Score;

import Modols.Account.Account;
import Modols.Product.Product;

public class Score {

    protected int scoreId;
    protected Account user;
    protected int score;
    protected Product good;

    //

    public Score(int scoreId, Account user, int score, Product good) {
        this.scoreId = scoreId;
        this.user = user;
        this.score = score;
        this.good = good;
    }
}
