package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.List;

public class BuyAndSellHistory implements Packable {

    public long historyId;
    private List<LogHistory> logHistoryList;

    public List<LogHistory> getLogHistoryList() {
        return logHistoryList;
    }

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(historyId,logHistoryList);
    }

    public BuyAndSellHistory(long historyId, List<LogHistory> logHistoryList) {
        this.historyId = historyId;
        this.logHistoryList = logHistoryList;
    }
}
