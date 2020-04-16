package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class BuyAndSellHistory implements Packable {

    public long historyId;
    private List<LogHistory> logHistoryList;

    public List<LogHistory> getLogHistoryList() {
        return logHistoryList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public BuyAndSellHistory(long historyId, List<LogHistory> logHistoryList) {
        this.historyId = historyId;
        this.logHistoryList = logHistoryList;
    }
}
