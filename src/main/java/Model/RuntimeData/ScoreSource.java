package Model.RuntimeData;

import Model.Models.Score;

import java.io.File;
import java.util.List;

public class ScoreSource {

    private static File scoreList_File = new File("src/main/resources/allScores");

    private List<Score> scoreList;

    public List<Score> getScoreList() {
        return scoreList;
    }

    public ScoreSource(List<Score> scoreList) {
        this.scoreList = scoreList;
    }
}
