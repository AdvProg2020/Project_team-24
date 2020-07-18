package B_Server.Structs;

import java.time.LocalDate;

public class MiniAuction {

    private final String auctionId;
    private final String auctionName;
    private final double auctionPercent;
    private final double auctionLimit;
    private final LocalDate start;
    private final LocalDate end;

    public String getAuctionName() {
        return auctionName;
    }

    public double getAuctionPercent() {
        return auctionPercent;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public double getAuctionLimit() {
        return auctionLimit;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public MiniAuction(String auctionId, String auctionName, double auctionPercent, double auctionLimit, LocalDate start, LocalDate end) {
        this.auctionId = auctionId;
        this.auctionName = auctionName;
        this.auctionPercent = auctionPercent;
        this.auctionLimit = auctionLimit;
        this.start = start;
        this.end = end;
    }
}
