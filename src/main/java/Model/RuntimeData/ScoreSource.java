package Model.RuntimeData;

import Model.Models.Score;

import java.io.File;
import java.util.List;

public class ScoreSource {

    private static File scoreList_File = new File("src/main/resources/allScores");

    private static List<Score> scoreList;

    static {

    }

    public static List<Score> getScoreList() {
        return scoreList;
    }
}
