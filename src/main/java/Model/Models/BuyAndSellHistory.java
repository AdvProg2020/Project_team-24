package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.io.File;
import java.util.List;

public class BuyAndSellHistory implements Packable {

    private static File buyAndSellHistorySource;
    private static List<BuyAndSellHistory> buyAndSellHistoryList;

    static {

    }

    private long historyId;
    private List<LogHistory> logHistoryList;

    public long getHistoryId() {
        return historyId;
    }

    public List<LogHistory> getLogHistoryList() {
        return logHistoryList;
    }

    public static List<BuyAndSellHistory> getBuyAndSellHistoryList() {
        return buyAndSellHistoryList;
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
